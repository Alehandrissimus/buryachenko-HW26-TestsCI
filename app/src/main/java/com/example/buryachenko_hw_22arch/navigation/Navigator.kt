package com.example.buryachenko_hw_22arch.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.example.buryachenko_hw_22arch.postInput.ui.PostInputFragment
import com.example.buryachenko_hw_22arch.postsList.ui.PostsFragment

class Navigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val container: Int
) {

    fun showPostsFragment() {
        fragmentManager.beginTransaction()
            .add(container, PostsFragment.newInstance())
            .commitAllowingStateLoss()
    }

    fun showPostInputFragment() {
        fragmentManager.beginTransaction()
            .add(container, PostInputFragment.newInstance())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun popBackStack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
    }

}
