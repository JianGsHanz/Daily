package com.example.common.api

class ApiException(var code: Int, override var message: String) : Exception()