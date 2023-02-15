package com.wayne.taipeiair.repository

import android.content.Context
import androidx.room.Room
import com.wayne.taipeiair.repository.entity.CityEntity

object Repository {
    private lateinit var dbManager: DBManager

    fun setUpRepository(context: Context) {
        dbManager = Room.databaseBuilder(context, DBManager::class.java, "DB").build()
    }

    internal fun CityEntity.insert() =
        dbManager.getCityEntityDao().insert(this)

    fun queryYearMonthsInRecordAsync() =
        dbManager.getCityEntityDao().queryYearMonthsInRecordAsync()

    fun queryCitiesByYearMonth(yearMonths: List<String>) =
        dbManager.getCityEntityDao().queryByYearMonth(yearMonths)
}