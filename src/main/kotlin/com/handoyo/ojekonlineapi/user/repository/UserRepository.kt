package com.handoyo.ojekonlineapi.user.repository

import com.handoyo.ojekonlineapi.user.entity.User


interface UserRepository {

    fun insertUser(user: User): Result<Boolean>

    fun getUserById(id: String): Result<User>

    fun getUserByUsername(username: String): Result<User>
}