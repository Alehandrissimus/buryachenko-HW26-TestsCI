package com.example.buryachenko_hw_22arch.navigation

import androidx.fragment.app.Fragment
import com.example.buryachenko_hw_22arch.NavigationActivity

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected val navigator: Navigator by lazy { (requireActivity() as NavigationActivity).navigator }
}
