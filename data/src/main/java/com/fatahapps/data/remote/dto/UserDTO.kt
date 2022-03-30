package com.fatahapps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("name") val name: String,
    @SerializedName("other_name") val otherName: String?,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String?,
    @SerializedName("password_confirmation") val passwordConfirmation: String?,
    @SerializedName("msisdn") val msisdn: String
)