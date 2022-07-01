package com.handoyo.ojekonlineapi.user.controller

import com.handoyo.ojekonlineapi.BaseResponse
import com.handoyo.ojekonlineapi.toResponses
import com.handoyo.ojekonlineapi.user.entity.LoginResponse
import com.handoyo.ojekonlineapi.user.entity.User
import com.handoyo.ojekonlineapi.user.entity.UserLogin
import com.handoyo.ojekonlineapi.user.entity.UserRequest
import com.handoyo.ojekonlineapi.user.services.UserServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/driver")
class DriverController {

    @Autowired
    private lateinit var userServices: UserServices

    @GetMapping
    fun getUser(
    ): BaseResponse<User> {
        val userId = SecurityContextHolder.getContext().authentication.principal as? String
        println("<><><><> $userId")
        return userServices.getUserByUserId(userId.orEmpty()).toResponses()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody userLogin: UserLogin
    ): BaseResponse<LoginResponse> {
        return userServices.login(userLogin).toResponses()
    }

    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: UserRequest
    ): BaseResponse<Boolean> {
        return userServices.register(userRequest.mapToNewDriver()).toResponses()
    }
}