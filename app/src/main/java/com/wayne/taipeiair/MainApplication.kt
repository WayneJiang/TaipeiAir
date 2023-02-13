package com.wayne.taipeiair

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.wayne.taipeiair.repository.Repository

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Repository.setUpRepository(this)
    }
}