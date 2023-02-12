package com.wayne.taipeiair.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wayne.taipeiair.repository.dao.ParkingInfoEntityDao
import com.wayne.taipeiair.repository.dao.UserEntityDao
import com.wayne.taipeiair.repository.entity.ParkingInfoEntity
import com.wayne.taipeiair.repository.entity.UserEntity

@Database(
    entities = [UserEntity::class, ParkingInfoEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class DBManager : RoomDatabase() {
    abstract fun getUserEntityDao(): UserEntityDao

    abstract fun getParkingInfoEntityDao(): ParkingInfoEntityDao
}