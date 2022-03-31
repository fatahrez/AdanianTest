package com.fatahapps.presentation.mapper

import com.fatahapps.domain.entities.KweaModels.ImageEntity
import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.KweaModels.ShopEntity
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.model.kweaModels.Image
import com.fatahapps.presentation.model.kweaModels.KweaItem
import com.fatahapps.presentation.model.kweaModels.Shop
import javax.inject.Inject

class KweaDomainPresentationMapper @Inject constructor(): Mapper<KweaItemEntity, KweaItem> {
    override fun from(e: KweaItem): KweaItemEntity {
        return KweaItemEntity(
            createdAt = e.createdAt,
            id = e.id,
            imageEntities = e.images?.map {
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
            shopEntity = e.shop?.let {
                ShopEntity(
                    it.createdAt,
                    e.shop.id,
                    e.shop.shopAddress,
                    e.shop.shopEmail,
                    e.shop.shopMsisdn,
                    e.shop.shopName,
                    e.shop.updatedAt,
                    e.shop.user?.let { it1 ->
                        UserEntity(
                            it1.name,
                            e.shop.user.otherName,
                            e.shop.user.email,
                            e.shop.user.password,
                            e.shop.user.passwordConfirmation,
                            e.shop.user.msisdn
                        )
                    },
                    e.shop.userId
                )
            },
            updatedAt = e.updatedAt
        )
    }

    override fun to(t: KweaItemEntity): KweaItem {
        return KweaItem(
            createdAt = t.createdAt,
            id = t.id,
            images = t.imageEntities?.map {
                Image(
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
            shop = t.shopEntity?.let {
                t.shopEntity!!.id?.let { it1 ->
                    Shop(
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
                                    User(
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