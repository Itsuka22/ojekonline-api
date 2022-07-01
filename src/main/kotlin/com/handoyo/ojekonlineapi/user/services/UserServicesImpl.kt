package com.handoyo.ojekonlineapi.user.services


import com.handoyo.ojekonlineapi.OjekonlineException
import com.handoyo.ojekonlineapi.authentication.JwtConfig
import com.handoyo.ojekonlineapi.user.entity.LoginResponse
import com.handoyo.ojekonlineapi.user.entity.User
import com.handoyo.ojekonlineapi.user.entity.UserLogin
import com.handoyo.ojekonlineapi.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServicesImpl(
    @Autowired
    private val userRepository: UserRepository
) : UserServices {

    override fun login(userLogin: UserLogin): Result<LoginResponse> {
        val resultUser = userRepository.getUserByUsername(userLogin.username)
        return resultUser.map {
            val token = JwtConfig.generateToken(it)
            val passwordInDb = it.password
            val passwordRequest = userLogin.password
            if (passwordInDb == passwordRequest) {
                LoginResponse(token)
            } else {
                throw OjekonlineException("Password invalid!")
            }
        }
    }

    override fun register(user: User): Result<Boolean> {
        return userRepository.insertUser(user)
    }

    override fun getUserByUserId(id: String): Result<User> {
        return userRepository.getUserById(id).map {
            it.password = null
            it
        }
    }

    override fun getUserByUsername(username: String): Result<User> {
        return userRepository.getUserByUsername(username).map {
            it.password = null
            it
        }
    }
}