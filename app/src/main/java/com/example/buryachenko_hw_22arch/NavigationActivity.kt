package com.example.buryachenko_hw_22arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.buryachenko_hw_22arch.navigation.Navigator

class NavigationActivity : AppCompatActivity() {


    val navigator by lazy { Navigator(supportFragmentManager, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navigator.showPostsFragment()
    }
}
