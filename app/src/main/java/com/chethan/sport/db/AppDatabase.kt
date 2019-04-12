package com.chethan.sport.db
/**
 * Created by Chethan on 4/8/2019.
 */

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chethan.sport.model.Item


@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemsDao
}
