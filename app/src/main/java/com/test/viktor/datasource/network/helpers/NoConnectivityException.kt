package com.test.viktor.datasource.network.helpers

import java.io.IOException

class NoConnectivityException(private val errorMessage: String) : IOException() {

    override fun getLocalizedMessage(): String = errorMessage
}