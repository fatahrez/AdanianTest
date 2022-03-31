package com.fatahapps.data.local.models

data class UserLocal(
    val name: String,
    val otherName: String?,
    val email: String,
    val password: String?,
    val passwordConfirmation: String?,
    val msisdn: String
)