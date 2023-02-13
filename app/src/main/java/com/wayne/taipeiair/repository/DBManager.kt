package com.wayne.taipeiair.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wayne.taipeiair.repository.dao.CityEntityDao
import com.wayne.taipeiair.repository.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class DBManager : RoomDatabase() {
    abstract fun getCityEntityDao(): CityEntityDao
}