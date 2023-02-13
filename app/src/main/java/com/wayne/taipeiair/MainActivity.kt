package com.wayne.taipeiair

import android.graphics.Color
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.wayne.taipeiair.databinding.ActivityMainBinding
import com.wayne.taipeiair.repository.Repository
import com.wayne.taipeiair.webservice.WebService

class MainActivity : AppCompatActivity() {
    private lateinit var mActivityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)

        window.apply {
            insetsController?.apply {
                hide(WindowInsets.Type.navigationBars())
                setDecorFitsSystemWindows(true)
                isNavigationBarContrastEnforced = false

                statusBarColor = Color.WHITE

                systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

                setSystemBarsAppearance(
                    (WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS),
                    (WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS)
                )
            }
        }

        WebService.requestData {  }
    }
}