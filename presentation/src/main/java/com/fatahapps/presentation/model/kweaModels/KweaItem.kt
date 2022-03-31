package com.fatahapps.presentation.model.kweaModels

data class KweaItem(
    val createdAt: String?,
    val id: Int,
    val images: List<Image>?,
    val itemCategory: String?,
    val itemCurrency: String?,
    val itemDescription: String?,
    val itemName: String?,
    val itemPrice: Int?,
    val shop: Shop?,
    val updatedAt: String?
)