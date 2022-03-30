package com.fatahapps.data.remote

import com.fatahapps.data.remote.dto.UserDTO
import com.fatahapps.data.remote.dto.UserSuccessDTO
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface EcobbaApi {

    companion object {
        const val BASE_URL = "https://dev.ecobba.com/api/"
    }

    @POST("register")
    suspend fun postUserRegistration(
        @Body user: UserDTO
    ): UserSuccessDTO

    @POST("login")
    suspend fun postUserSignIn(
        @Query("email") email: String,
        @Query("password") password: String
    )

}