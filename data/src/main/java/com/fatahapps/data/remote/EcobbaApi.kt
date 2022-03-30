package com.fatahapps.data.remote

import com.fatahapps.data.remote.dto.UserDTO
import com.fatahapps.data.remote.dto.UserSuccessDTO
import retrofit2.http.*

interface EcobbaApi {

    companion object {
        const val BASE_URL = "https://dev.ecobba.com/api/"
    }

    @POST("register")
    suspend fun postUserRegistration(
        @Body user: UserDTO
    ): UserSuccessDTO

    @FormUrlEncoded
    @POST("login")
    suspend fun postUserSignIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): UserSuccessDTO

}