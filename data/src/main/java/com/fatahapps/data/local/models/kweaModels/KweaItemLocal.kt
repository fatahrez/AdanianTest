package com.fatahapps.data.local.models.kweaModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KweaItemLocal(
    val createdAt: String?,
    @PrimaryKey val id: Int,
    val imageLocals: List<ImageLocal>?,
    val itemCategory: String?,
    val itemCurrency: String?,
    val itemDescription: String?,
    val itemName: String?,
    val itemPrice: Int?,
    val shopLocal: ShopLocal?,
    val updatedAt: String?
)