package com.wayne.taipeiair.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wayne.taipeiair.repository.Repository
import com.wayne.taipeiair.repository.entity.CityEntity

class GoodViewModel : ViewModel() {
    companion object {
        var goodLiveData = MutableLiveData<List<CityEntity>>(emptyList())
    }
}