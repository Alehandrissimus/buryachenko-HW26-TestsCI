package com.example.buryachenko_hw22_arch.domain

class UsersTaggedStorage private constructor() {

    companion object {
        private var usersTaggedStorage: UsersTaggedStorage? = null

        @Synchronized
        fun getInstance(): UsersTaggedStorage {
            if(usersTaggedStorage == null) {
                usersTaggedStorage = UsersTaggedStorage()
            }
            return usersTaggedStorage ?: UsersTaggedStorage()
        }
    }
}