package com.chethan.sport.db.db

/**
 * Created by Chethan on 4/8/2019.
 */
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.chethan.sport.model.Item
import com.chethan.sport.util.LiveDataTestUtil.getValue
import com.chethan.sport.util.TestUtil
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemsDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    // All the db operation can be seen in below functions
    @Test
    fun insertAndRead() {

        // insert data and load data
        val item =
            TestUtil.createItem(1000, "Easy walk steps", "Walk 500 steps a day", "step", "500", "bronze_medal", 5)
        // list of goal items
        val itemArrayList: List<Item> = TestUtil.createItemArrayList(10, item)
        db.itemDao().insertAll(itemArrayList)

        // check data is not null
        val loaded = getValue(db.itemDao().loadAllTheUserGoals())
        assertThat(loaded, notNullValue())

        // check data element
        assertThat(loaded.get(0).title, `is`("Easy walk steps"))
        assertThat(loaded.get(0).description, `is`("Walk 500 steps a day"))
        assertThat(loaded.get(0).type, `is`("step"))

        // check data size
        val list = getValue(db.itemDao().loadAllTheUserGoals())
        assertThat(list.size, `is`(10))

    }


    @Test
    fun createIfNotExists_exists() {
        val item =
            TestUtil.createItem(1011, "Easy walk steps", "Walk 500 steps a day", "step", "500", "bronze_medal", 5)
        db.itemDao().insert(item)
        assertThat(db.itemDao().createItemIfNotExists(item), `is`(-1L))
    }

    @Test
    fun createIfNotExists_doesNotExist() {
        val item =
            TestUtil.createItem(1, "Medium walk steps", "Walk 1000 steps a day", "step", "1000", "silver_medal", 10)
        assertThat(db.itemDao().createItemIfNotExists(item), `is`(1L))
    }


    @Test
    fun deleteSingleItem() {
        // insert 10 items
        val item =
            TestUtil.createItem(1000, "Easy walk steps", "Walk 500 steps a day", "step", "500", "bronze_medal", 5)
        // list of goal items
        val itemArrayList: List<Item> = TestUtil.createItemArrayList(10, item)
        db.itemDao().insertAll(itemArrayList)
        // Delete single item, Number of item present this time is 10
        db.itemDao().delete(item)
        // check data size after deletion
        val listAfterDelete = getValue(db.itemDao().loadAllTheUserGoals())
        assertThat(listAfterDelete.size, `is`(9))

    }


    @Test
    fun deleteAll() {
        // Delete all the items
        db.itemDao().deleteAll()
        // check data size after deletion
        val listOfItemsAfterDeletingAll = getValue(db.itemDao().loadAllTheUserGoals())
        assertThat(listOfItemsAfterDeletingAll.size, `is`(0))
    }

    @Test
    fun insertItem() {
        val item =
            TestUtil.createItem(1000, "Easy walk steps", "Walk 500 steps a day", "step", "500", "bronze_medal", 5)

        // insertion operation
        db.itemDao().insert(item)
        // check data size, it should be 1
        val listAfterInsert = getValue(db.itemDao().loadAllTheUserGoals())
        assertThat(listAfterInsert.size, `is`(1))
    }


}
