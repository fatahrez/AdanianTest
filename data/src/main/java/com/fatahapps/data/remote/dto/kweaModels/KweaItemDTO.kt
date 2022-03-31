package com.fatahapps.data.remote.dto.kweaModels

import com.google.gson.annotations.SerializedName

data class KweaItemDTO(
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("images") val imageDTOS: List<ImageDTO>?,
    @SerializedName("item_category") val itemCategory: String?,
    @SerializedName("item_currency") val itemCurrency: String?,
    @SerializedName("item_description") val itemDescription: String?,
    @SerializedName("item_name") val itemName: String?,
    @SerializedName("item_price") val itemPrice: Int?,
    @SerializedName("shop") val shopDTO: ShopDTO?,
    @SerializedName("updated_at") val updatedAt: String?
)