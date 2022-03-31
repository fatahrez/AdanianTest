package com.fatahapps.domain.entities.KweaModels

import com.fatahapps.domain.entities.UserEntity

data class ShopEntity(
    val createdAt: String?,
    val id: Int?,
    val shopAddress: String?,
    val shopEmail: String?,
    val shopMsisdn: String?,
    val shopName: String?,
    val updatedAt: String?,
    val user: UserEntity?,
    val userId: Int?
)