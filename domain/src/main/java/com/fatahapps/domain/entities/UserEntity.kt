package com.fatahapps.domain.entities

data class UserEntity(
    val name: String,
    val otherName: String?,
    val email: String,
    val password: String?,
    val passwordConfirmation: String?,
    val msisdn: String
)
