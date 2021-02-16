package com.example.buryachenko_hw22_arch.present

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.buryachenko_hw22_arch.*
import com.example.buryachenko_hw22_arch.present.model.NavigationModel
import com.example.buryachenko_hw22_arch.present.model.ViewModelFactory
import com.example.buryachenko_hw22_arch.tools.AppModule
import com.example.buryachenko_hw22_arch.tools.DaggerAppComponent
import com.example.buryachenko_hw22_arch.tools.Navigator
import javax.inject.Inject

class  NavigationActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val navigationModel: NavigationModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[NavigationModel::class.java]
    }
    val navigator by lazy { Navigator(supportFragmentManager, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        initInjector()

        navigationModel.getRepoList()
        navigator.showPostsFragment()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun initInjector() {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }
}