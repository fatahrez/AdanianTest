package com.fatahapps.data.local.models.kweaModels

import com.fatahapps.data.local.models.UserLocal
import com.fatahapps.domain.entities.UserEntity

data class ShopLocal(
    val createdAt: String?,
    val id: Int,
    val shopAddress: String?,
    val shopEmail: String?,
    val shopMsisdn: String?,
    val shopName: String?,
    val updatedAt: String?,
    val user: UserLocal?,
    val userId: Int?
)