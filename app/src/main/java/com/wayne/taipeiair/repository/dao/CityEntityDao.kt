package com.wayne.taipeiair.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wayne.taipeiair.repository.entity.CityEntity
import com.wayne.taipeiair.webservice.json.City
import java.time.YearMonth

@Dao
internal interface CityEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityEntity: CityEntity)

    @Query("SELECT YEAR_MONTH FROM CITY GROUP BY YEAR_MONTH")
    fun queryYearMonthsInRecord(): LiveData<List<String>>

    @Query("SELECT * FROM CITY WHERE YEAR_MONTH = :yearMonth")
    fun queryByYearMonth(yearMonth: String): LiveData<List<CityEntity>>
}