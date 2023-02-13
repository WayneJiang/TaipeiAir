package com.wayne.taipeiair.viewmodel

import androidx.lifecycle.ViewModel
import com.wayne.taipeiair.repository.Repository

class MainViewModel : ViewModel() {
    fun queryYearMonthsInRecord() = Repository.queryYearMonthsInRecord()

    fun queryCitiesByYearMonth(yearMonth: String) = Repository.queryCitiesByYearMonth(yearMonth)
}