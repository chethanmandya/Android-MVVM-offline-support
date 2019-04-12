package com.chethan.sport.repository


import androidx.lifecycle.LiveData
import com.chethan.demoproject.utils.RateLimiter
import com.chethan.sport.AppExecutors
import com.chethan.sport.api.NetWorkApi
import com.chethan.sport.db.ItemsDao
import com.chethan.sport.model.Item
import com.chethan.sport.model.ItemList
import com.chethan.sport.testing.OpenForTesting
import java.util.concurrent.TimeUnit

@OpenForTesting
class DataRepository(val appExecutors: AppExecutors, val netWorkApi: NetWorkApi, val itemsDao: ItemsDao) {


    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)
    private val TAG = "DataRepository"

    fun getGoalList(): LiveData<Resource<List<Item>>> {
        return object : NetworkBoundResource<List<Item>, ItemList>(appExecutors) {

            override fun saveCallResult(item: ItemList) {
                    itemsDao.insertAll(item.items)
            }

            override fun shouldFetch(data: List<Item>?): Boolean {
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch("Items")
            }

            override fun loadFromDb() = itemsDao.loadAllTheUserGoals()

            override fun createCall() = netWorkApi.getGoals()

            override fun onFetchFailed() {
                repoListRateLimit.reset("Items")
            }
        }.asLiveData()
    }

}

