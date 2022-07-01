package com.handoyo.ojekonlineapi

inline fun <reified T> T?.orThrow(
    message: String = "${T::class.simpleName} is null"
): T {
    return this ?: throw OjekonlineException(message)
}



inline fun <reified T> T?.toResult(
    message: String = "${T::class.simpleName} is null"
): Result<T> {
    return if (this != null) {
        Result.success(this)
    } else {
        Result.failure(OjekonlineException(message))
    }
}

fun <T>Result<T>.toResponses(): com.handoyo.ojekonlineapi.BaseResponse<T> {
    return if (this.isFailure) {
        throw OjekonlineException(this.exceptionOrNull()?.message ?: "Failure")
    } else {
        BaseResponse.success(this.getOrNull())
    }
}