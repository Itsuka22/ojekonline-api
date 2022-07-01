package com.handoyo.ojekonlineapi.user.entity

data class UserRequest(
    val username: String = "",
    val password: String = "",
    var email: String = "",
    var address: String = "",
    var no_handphone: String = "",
    var firstname: String = "",
    var lastname: String = ""
) {
    fun mapToUser(): User {
        return User.createNewUser(username, password, email, address, no_handphone, firstname, lastname)
    }

    fun mapToNewCustomer(): User {
        return User.createNewCustomer(username, password, email, address, no_handphone, firstname, lastname)
    }

    fun mapToNewDriver(): User {
        return User.createNewDriver(username, password, email, address, no_handphone, firstname, lastname)
    }

}