package com.example.buryachenko_hw22_arch.present.fragments

import androidx.fragment.app.Fragment
import com.example.buryachenko_hw22_arch.present.NavigationActivity
import com.example.buryachenko_hw22_arch.app.Navigator

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected val navigator: Navigator by lazy { (requireActivity() as NavigationActivity).navigator }
}