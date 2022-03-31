package com.fatahapps.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.fatahapps.data.local.models.kweaModels.ImageLocal
import com.fatahapps.data.local.models.kweaModels.ShopLocal
import com.fatahapps.data.local.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters (
    private val jsonParser: JsonParser
){
    @TypeConverter
    fun fromImagesJson(json: String): List<ImageLocal>? {
        return jsonParser.fromJson<ArrayList<ImageLocal>>(
            json,
            object : TypeToken<ArrayList<ImageLocal>>(){}.type
        )
    }

    @TypeConverter
    fun toImagesJson(images: List<ImageLocal>?): String {
        return jsonParser.toJson(
            images,
            object : TypeToken<ArrayList<ImageLocal>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromShopJson(json: String): ShopLocal? {
        return jsonParser.fromJson<ShopLocal>(
            json,
            object : TypeToken<ShopLocal>(){}.type
        )
    }

    @TypeConverter
    fun toShopJson(shopLocal: ShopLocal?): String {
        return jsonParser.toJson(
            shopLocal,
            object : TypeToken<ShopLocal>(){}.type
        ) ?: ""
    }
}