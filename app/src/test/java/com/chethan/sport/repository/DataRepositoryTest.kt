/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chethan.sport.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chethan.sport.api.NetWorkApi
import com.chethan.sport.db.AppDatabase
import com.chethan.sport.db.ItemsDao
import com.chethan.sport.model.Item
import com.chethan.sport.model.ItemList
import com.chethan.sport.util.ApiUtil.successCall
import com.chethan.sport.util.InstantAppExecutors
import com.chethan.sport.util.TestUtil
import com.chethan.sport.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class DataRepositoryTest {
    private lateinit var repository: DataRepository
    private val dao = mock(ItemsDao::class.java)
    private val service = mock(NetWorkApi::class.java)
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock(AppDatabase::class.java)
        `when`(db.itemDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = DataRepository(InstantAppExecutors(), service, dao)
    }

    @Test
    fun goToNetwork() {
        val dbData = MutableLiveData<List<Item>>()
        `when`(dao.loadAllTheUserGoals()).thenReturn(dbData)

        // insert data and load data
        val item =
            TestUtil.createItem(1000, "Easy walk steps", "Walk 500 steps a day", "step", "500", "bronze_medal", 5)
        // list of goal items
        val itemArrayList: List<Item> = TestUtil.createItemArrayList(10, item)
        val itemList : ItemList = ItemList(itemArrayList, "0")

        val call = successCall(itemList)
        `when`(service.getGoals()).thenReturn(call)


        val data = repository.getGoalList()
        verify(dao).loadAllTheUserGoals()
        verifyNoMoreInteractions(service)


        val observer = mock<Observer<Resource<List<Item>>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<List<Item>>()
        `when`(dao.loadAllTheUserGoals()).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(service).getGoals()

        updatedDbData.postValue(itemArrayList)
        verify(observer).onChanged(Resource.success(itemArrayList))
    }

    @Test
    fun dontGoToNetwork() {

        // insert data and load data
        val item =
            TestUtil.createItem(1000, "Easy walk steps", "Walk 500 steps a day", "step", "500", "bronze_medal", 5)
        // list of goal items
        val itemArrayList: List<Item> = TestUtil.createItemArrayList(10, item)
        val itemList : ItemList = ItemList(itemArrayList, "0")


        val dbData = MutableLiveData<List<Item>>()
        dbData.value = itemArrayList
        `when`(dao!!.loadAllTheUserGoals()).thenReturn(dbData)
        verify(service, never()).getGoals()
    }
}