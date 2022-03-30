package com.fatahapps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserSuccessDTO(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("user") val user: UserDTO,
    @SerializedName("token") val token: String,
    @SerializedName("errors") val errors: String?
)
