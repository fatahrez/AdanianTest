package com.fatahapps.data.remote.dto.kweaModels

import com.google.gson.annotations.SerializedName

data class KweaItemsWrapper(
    @SerializedName("data") val data: List<KweaItemDTO>
)
