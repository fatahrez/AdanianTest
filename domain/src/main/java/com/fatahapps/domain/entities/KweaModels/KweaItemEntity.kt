package com.fatahapps.domain.entities.KweaModels

data class KweaItemEntity(
    val createdAt: String?,
    val id: Int,
    val imageEntities: List<ImageEntity>?,
    val itemCategory: String?,
    val itemCurrency: String?,
    val itemDescription: String?,
    val itemName: String?,
    val itemPrice: Int?,
    val shopEntity: ShopEntity?,
    val updatedAt: String?
)