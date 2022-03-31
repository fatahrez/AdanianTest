package com.fatahapps.presentation.model

data class User(
    val name: String,
    val otherName: String?,
    val email: String,
    val password: String?,
    val passwordConfirmation: String?,
    val msisdn: String
)
