package com.chethan.sport.db
/**
 * Created by Chethan on 4/3/2019.
 */

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chethan.sport.model.Item




@Dao
public interface ItemsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<Item>)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM Item")
    fun deleteAll()

    @Query("SELECT * FROM Item")
    fun loadAllTheUserGoals(): LiveData<List<Item>>

    @Query("SELECT * FROM Item where id = :itemId")
    fun loadUserGoals(itemId: Int): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createItemIfNotExists(item: Item): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg item: Item)
}
