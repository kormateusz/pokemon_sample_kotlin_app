package pl.kormateusz.pokemon.data.utils

import retrofit2.HttpException
import retrofit2.Response

object EmptyBodyException : Exception("Body of response is empty")

fun <T, E> Response<T>.makeRequest(onSuccess: (T) -> E): Result<E> {
    return try {
        val body = this.body()
        if (body != null) {
            Result.success(onSuccess(body))
        } else {
            Result.failure(EmptyBodyException)
        }
    } catch (httpException: HttpException) {
        Result.failure(httpException)
    }
}