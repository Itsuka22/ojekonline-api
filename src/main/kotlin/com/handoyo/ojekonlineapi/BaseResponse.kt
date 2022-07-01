package com.handoyo.ojekonlineapi

open class BaseResponse<T>(
    var status: Boolean = true,
    var message: String = "success",
    var data: T? = null
) {
    companion object {
        fun <T>success(data: T?): com.handoyo.ojekonlineapi.BaseResponse<T> {
            return com.handoyo.ojekonlineapi.BaseResponse(data = data)
        }

        fun <T>failure(message: String): com.handoyo.ojekonlineapi.BaseResponse<T> {
            return com.handoyo.ojekonlineapi.BaseResponse(status = false, message = message)
        }
    }
}