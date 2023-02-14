package com.wayne.taipeiair.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayne.taipeiair.repository.Repository
import com.wayne.taipeiair.repository.entity.CityEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val yearMonthFilterLiveData = MutableLiveData(listOf("111年10月"))

    val categoryFilterLiveData = MutableLiveData(1)

    val goodLiveData = MutableLiveData<List<CityEntity>>(emptyList())
    val notBadLiveData = MutableLiveData<List<CityEntity>>(emptyList())
    val badLiveData = MutableLiveData<List<CityEntity>>(emptyList())

    private var mCategoryFilter = 1

    private var mCities = listOf<CityEntity>()

    fun queryYearMonthsInRecordAsync() = Repository.queryYearMonthsInRecordAsync()

    fun queryCitiesByYearMonth(yearMonths: List<String>) =
        viewModelScope.launch(Dispatchers.IO) {
            mCities = Repository.queryCitiesByYearMonth(yearMonths)

            groupCities()
        }

    private fun groupCities() {
        goodLiveData.postValue(
            mCities.filter {
                when (mCategoryFilter) {
                    1 -> {
                        it.value1 <= 30
                    }
                    2 -> {
                        it.value2 <= 30
                    }
                    3 -> {
                        it.value3 <= 30
                    }
                    4 -> {
                        it.value4 <= 30
                    }
                    5 -> {
                        it.value5 <= 30
                    }
                    6 -> {
                        it.value6 <= 30
                    }
                    else -> {
                        it.value7 <= 30
                    }
                }
            }
        )

        notBadLiveData.postValue(
            mCities.filter {
                when (mCategoryFilter) {
                    1 -> {
                        (it.value1 > 30) and (it.value1 < 45)
                    }
                    2 -> {
                        (it.value2 > 30) and (it.value2 < 45)
                    }
                    3 -> {
                        (it.value3 > 30) and (it.value3 < 45)
                    }
                    4 -> {
                        (it.value4 > 30) and (it.value4 < 45)
                    }
                    5 -> {
                        (it.value5 > 30) and (it.value5 < 45)
                    }
                    6 -> {
                        (it.value6 > 30) and (it.value6 < 45)
                    }
                    else -> {
                        (it.value7 > 30) and (it.value7 < 45)
                    }
                }
            }
        )

        badLiveData.postValue(
            mCities.filter {
                when (mCategoryFilter) {
                    1 -> {
                        it.value1 >= 45
                    }
                    2 -> {
                        it.value2 >= 45
                    }
                    3 -> {
                        it.value3 >= 45
                    }
                    4 -> {
                        it.value4 >= 45
                    }
                    5 -> {
                        it.value5 >= 45
                    }
                    6 -> {
                        it.value6 >= 45
                    }
                    else -> {
                        it.value7 >= 45
                    }
                }
            }
        )
    }
}