package com.fatahapps.domain.entities

data class UserSuccessEntity(
    val success: Boolean,
    val message: String,
    val user: UserEntity,
    val token: String
)
