package com.example.buryachenko_hw22_arch.navigation

import androidx.fragment.app.Fragment
import com.example.buryachenko_hw22_arch.NavigationActivity

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected val navigator: Navigator by lazy { (requireActivity() as NavigationActivity).navigator }
}