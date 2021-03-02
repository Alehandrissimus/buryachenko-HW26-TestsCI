package com.example.buryachenko_hw22_arch.postsList.data

import com.example.buryachenko_hw22_arch.postsList.data.models.UserStatus
import com.example.buryachenko_hw22_arch.postsList.data.models.UserStatused

class UsersStatusedStorage private constructor() {

    private val users = mutableSetOf<UserStatused>()

    init {
        users.addAll(setup())
    }

    fun getList(): MutableSet<UserStatused> = users

    private fun setup(): MutableSet<UserStatused> {
        return mutableSetOf(
            UserStatused(3, UserStatus.WARNING),
            UserStatused(4, UserStatus.WARNING),
            UserStatused(7, UserStatus.BANNED)
        )
    }

    companion object {
        private var usersStatusedStorage: UsersStatusedStorage? = null

        @Synchronized
        fun getInstance(): UsersStatusedStorage {
            if (usersStatusedStorage == null) {
                usersStatusedStorage = UsersStatusedStorage()
            }
            return usersStatusedStorage ?: UsersStatusedStorage()
        }
    }
}