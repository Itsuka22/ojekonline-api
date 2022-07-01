package com.handoyo.ojekonlineapi.user.entity

import java.util.*

data class User(
    var id: String = "",
    var username: String = "",
    var password: String? = "",
    var email: String = "",
    var address: String = "",
    var no_handphone: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var role: Int = RoleEnum.CUSTOMER.id
) {
    companion object {
        fun createNewUser(username: String, password: String, email: String,
                          address: String,
                          no_handphone : String,
                          firstname: String,
                          lastname: String): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password,
                email = email,
                address = address,
                no_handphone = no_handphone,
                firstname = firstname,
                lastname = lastname
            )
        }

        fun createNewCustomer(
            username: String,
            password: String,
            email: String,
            address: String,
            no_handphone : String,
            firstname: String,
            lastname: String
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password,
                email = email,
                address = address,
                no_handphone = no_handphone,
                firstname = firstname,
                lastname = lastname,
                role = RoleEnum.CUSTOMER.id
            )
        }

        fun createNewDriver(
            username: String,
            password: String,
            email: String,
            address: String,
            no_handphone: String,
            firstname: String,
            lastname: String
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password,
                email = email,
                address = address,
                lastname = no_handphone,
                no_handphone = firstname,
                firstname = lastname,
                role = RoleEnum.DRIVER.id
            )
        }

    }
}