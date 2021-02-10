package com.example.buryachenko_hw22_arch.domain

class UsersStatusedStorage private constructor() {

    private val userList = mutableSetOf<UserStatused>()

    init {
        userList.addAll(setup())
    }

    fun addUser(user: Int, status: UserStatus) {
        userList.add(UserStatused(user, status))
    }

    fun getList(): MutableSet<UserStatused> = userList

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