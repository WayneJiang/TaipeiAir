package com.wayne.taipeiair

import androidx.lifecycle.ViewModel
import com.wayne.taipeiair.repository.Repository

open class AppViewModel : ViewModel() {
    fun queryAllParkingInfo() = Repository.queryAllParkingInfoEntityAsync()

    fun queryUser() = Repository.queryUserEntityAsync()
}