package com.example.buryachenko_hw_22arch.postsList.data

import com.example.buryachenko_hw_22arch.postsList.data.models.UserStatus
import com.example.buryachenko_hw_22arch.postsList.data.models.UserStatused

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
        private var userStorage: UsersStatusedStorage? = null

        @Synchronized
        fun getInstance(): UsersStatusedStorage {
            if (userStorage == null) {
                userStorage = UsersStatusedStorage()
            }
            return userStorage ?: UsersStatusedStorage()
        }
    }
}
