package com.fatahapps.data.remote

import com.fatahapps.data.remote.dto.UserDTO
import com.fatahapps.data.remote.dto.UserSuccessDTO
import com.fatahapps.data.remote.dto.kweaModels.KweaItemDTO
import com.fatahapps.data.remote.dto.kweaModels.KweaItemsWrapper
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

    @GET("kwea-items")
    suspend fun getKweaItems(
        @Header("Authorization") token: String
    ): KweaItemsWrapper

}