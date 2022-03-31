package com.fatahapps.data.remote.dto.kweaModels

data class KweaItemDTO(
    val createdAt: String?,
    val id: Int,
    val imageDTOS: List<ImageDTO>?,
    val itemCategory: String?,
    val itemCurrency: String?,
    val itemDescription: String?,
    val itemName: String?,
    val itemPrice: Int?,
    val shopDTO: ShopDTO?,
    val updatedAt: String?
)