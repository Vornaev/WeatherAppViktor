package com.test.viktor.datasource.network.helpers


sealed class ApiResult<out T>(val data: T?, val networkError: String?) {

    data class Success<out R>(private val _data: R?) : ApiResult<R>(
        data = _data,
        networkError = null
    )

    data class Error(private val error: String) : ApiResult<Nothing>(
        data = null,
        networkError = error
    )

   object Loading : ApiResult<Nothing>(
        data = null,
        networkError = null
    )
}