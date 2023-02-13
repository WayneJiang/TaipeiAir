package com.wayne.taipeiair.repository

import android.content.Context
import androidx.room.Room
import com.wayne.taipeiair.repository.entity.CityEntity
import kotlinx.coroutines.*
import java.time.YearMonth
import kotlin.coroutines.CoroutineContext

object Repository {
    private lateinit var dbManager: DBManager

    fun setUpRepository(context: Context) {
        dbManager = Room.databaseBuilder(context, DBManager::class.java, "DB").build()
    }

    internal fun CityEntity.insert() =
        dbManager.getCityEntityDao().insert(this)

    fun queryYearMonthsInRecord() =
        dbManager.getCityEntityDao().queryYearMonthsInRecord()

    fun queryCitiesByYearMonth(yearMonth: String) =
        dbManager.getCityEntityDao().queryByYearMonth(yearMonth)
}