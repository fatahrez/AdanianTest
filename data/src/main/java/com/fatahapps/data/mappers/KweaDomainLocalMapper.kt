package com.fatahapps.data.mappers

import com.fatahapps.data.local.models.UserLocal
import com.fatahapps.data.local.models.kweaModels.ImageLocal
import com.fatahapps.data.local.models.kweaModels.KweaItemLocal
import com.fatahapps.data.local.models.kweaModels.ShopLocal
import com.fatahapps.domain.entities.KweaModels.ImageEntity
import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.KweaModels.ShopEntity
import com.fatahapps.domain.entities.UserEntity
import javax.inject.Inject

class KweaDomainLocalMapper @Inject constructor(): Mapper<KweaItemEntity, KweaItemLocal> {
    override fun from(e: KweaItemLocal): KweaItemEntity {
        return KweaItemEntity(
            createdAt = e.createdAt,
            id = e.id,
            imageEntities = e.imageLocals?.map {
                ImageEntity(
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
            shopEntity = e.shopLocal?.let {
                ShopEntity(
                    it.createdAt,
                    e.shopLocal.id,
                    e.shopLocal.shopAddress,
                    e.shopLocal.shopEmail,
                    e.shopLocal.shopMsisdn,
                    e.shopLocal.shopName,
                    e.shopLocal.updatedAt,
                    e.shopLocal.user?.let { it1 ->
                        UserEntity(
                            it1.name,
                            e.shopLocal.user.otherName,
                            e.shopLocal.user.email,
                            e.shopLocal.user.password,
                            e.shopLocal.user.passwordConfirmation,
                            e.shopLocal.user.msisdn
                        )
                    },
                    e.shopLocal.userId
                )
            },
            updatedAt = e.updatedAt
        )
    }

    override fun to(t: KweaItemEntity): KweaItemLocal {
        return KweaItemLocal(
            createdAt = t.createdAt,
            id = t.id,
            imageLocals = t.imageEntities?.map {
                ImageLocal(
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
            shopLocal = t.shopEntity?.let {
                t.shopEntity!!.id?.let { it1 ->
                    ShopLocal(
                        it.createdAt,
                        it1,
                        t.shopEntity!!.shopAddress,
                        t.shopEntity!!.shopEmail,
                        t.shopEntity!!.shopMsisdn,
                        t.shopEntity!!.shopName,
                        t.shopEntity!!.updatedAt,
                        t.shopEntity!!.user?.let { it2 ->
                            t.shopEntity!!.user?.let { it3 ->
                                t.shopEntity!!.user?.let { it4 ->
                                    UserLocal(
                                        it2.name,
                                        t.shopEntity!!.user?.otherName,
                                        it3.email,
                                        t.shopEntity!!.user?.password,
                                        t.shopEntity!!.user?.passwordConfirmation,
                                        it4.msisdn
                                    )
                                }
                            }
                        },
                        t.shopEntity!!.userId
                    )
                }
            },
            updatedAt = t.updatedAt
        )
    }
}