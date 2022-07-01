package com.handoyo.ojekonlineapi.user.services

import com.handoyo.ojekonlineapi.user.entity.LoginResponse
import com.handoyo.ojekonlineapi.user.entity.User
import com.handoyo.ojekonlineapi.user.entity.UserLogin


interface UserServices {
    fun login(userLogin: UserLogin): Result<LoginResponse>
    fun register(user: User): Result<Boolean>
    fun getUserByUserId(id: String): Result<User>
    fun getUserByUsername(username: String): Result<User>
}