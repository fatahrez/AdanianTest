package com.fatahapps.presentation.model


data class UserSuccess(
    val success: Boolean,
    val message: String,
    val user: User,
    val token: String,
    val errors: String?
)
