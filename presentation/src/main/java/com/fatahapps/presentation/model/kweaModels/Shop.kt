package com.fatahapps.presentation.model.kweaModels

import com.fatahapps.presentation.model.User

data class Shop(
    val createdAt: String?,
    val id: Int?,
    val shopAddress: String?,
    val shopEmail: String?,
    val shopMsisdn: String?,
    val shopName: String?,
    val updatedAt: String?,
    val user: User?,
    val userId: Int?
)