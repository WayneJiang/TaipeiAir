package com.wayne.taipeiair.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "CITY", primaryKeys = ["YEAR_MONTH", "NAME"])
data class CityEntity(
    @ColumnInfo(name = "YEAR_MONTH")
    var yearMonth: String = "",
    @ColumnInfo(name = "YEAR_MONTH_INT")
    var yearMonthInt: Int = -9999,
    @ColumnInfo(name = "NAME")
    var name: String = "",
    @ColumnInfo(name = "VALUE_1")
    var value1: Double = -9999.0,
    @ColumnInfo(name = "VALUE_2")
    var value2: Double = -9999.0,
    @ColumnInfo(name = "VALUE_3")
    var value3: Double = -9999.0,
    @ColumnInfo(name = "VALUE_4")
    var value4: Double = -9999.0,
    @ColumnInfo(name = "VALUE_5")
    var value5: Double = -9999.0,
    @ColumnInfo(name = "VALUE_6")
    var value6: Double = -9999.0,
    @ColumnInfo(name = "VALUE_7")
    var value7: Double = -9999.0
)