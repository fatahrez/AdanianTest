package com.fatahapps.data.mappers

import com.fatahapps.data.local.models.UserLocal
import com.fatahapps.data.local.models.kweaModels.ImageLocal
import com.fatahapps.data.local.models.kweaModels.KweaItemLocal
import com.fatahapps.data.local.models.kweaModels.ShopLocal
import com.fatahapps.data.remote.dto.kweaModels.ImageDTO
import com.fatahapps.data.remote.dto.kweaModels.KweaItemDTO
import com.fatahapps.data.remote.dto.kweaModels.ShopDTO
import com.fatahapps.domain.entities.KweaModels.ImageEntity
import com.fatahapps.domain.entities.KweaModels.ShopEntity
import com.fatahapps.domain.entities.UserEntity
import javax.inject.Inject

class KweaLocalDTOMapper @Inject constructor(): Mapper<KweaItemLocal, KweaItemDTO> {
    override fun from(e: KweaItemDTO): KweaItemLocal {
        return KweaItemLocal(
            createdAt = e.createdAt,
            id = e.id,
            imageLocals = e.imageDTOS?.map {
                ImageLocal(
                    it.createdAt,
                    it.id,
                    it.image,
                    it.updatedAt
                )
            },
            itemCategory = e.itemCategory,
            itemCurrency = e.itemCurrency,
            itemDescription = e.itemDescription,
            itemName = e.itemName,
            itemPrice = e.itemPrice,
            shopLocal = e.shopDTO?.let {
                ShopLocal(
                    it.createdAt,
                    e.shopDTO.id,
                    e.shopDTO.shopAddress,
                    e.shopDTO.shopEmail,
                    e.shopDTO.shopMsisdn,
                    e.shopDTO.shopName,
                    e.shopDTO.updatedAt,
                    e.shopDTO.user?.let { it1 ->
                        UserLocal(
                            it1.name,
                            e.shopDTO.user.otherName,
                            e.shopDTO.user.email,
                            e.shopDTO.user.password,
                            e.shopDTO.user.passwordConfirmation,
                            e.shopDTO.user.msisdn
                        )
                    },
                    e.shopDTO.userId
                )
            },
            updatedAt = e.updatedAt
        )
    }

    override fun to(t: KweaItemLocal): KweaItemDTO {
        return KweaItemDTO(
            createdAt = t.createdAt,
            id = t.id,
            imageDTOS = t.imageLocals?.map {
                ImageDTO(
                    it.createdAt,
                    it.id,
                    it.image,
                    it.updatedAt
                )
            },
            itemCategory = t.itemCategory,
            itemCurrency = t.itemCurrency,
            itemDescription = t.itemDescription,
            itemName = t.itemName,
            itemPrice = t.itemPrice,
            shopDTO = t.shopLocal?.let {
                ShopDTO(
                    it.createdAt,
                    t.shopLocal.id,
                    t.shopLocal.shopAddress,
                    t.shopLocal.shopEmail,
                    t.shopLocal.shopMsisdn,
                    t.shopLocal.shopName,
                    t.shopLocal.updatedAt,
                    t.shopLocal.user?.let { it1 ->
                        UserLocal(
                            it1.name,
                            t.shopLocal.user.otherName,
                            t.shopLocal.user.email,
                            t.shopLocal.user.password,
                            t.shopLocal.user.passwordConfirmation,
                            t.shopLocal.user.msisdn
                        )
                    },
                    t.shopLocal.userId
                )
            },
            updatedAt = t.updatedAt
        )
    }
}