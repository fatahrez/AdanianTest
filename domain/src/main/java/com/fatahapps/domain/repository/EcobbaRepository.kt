package com.fatahapps.domain.repository

import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import kotlinx.coroutines.flow.Flow

interface EcobbaRepository {
    fun postUserRegistration(
        user: UserEntity
    ): Flow<Resource<UserSuccessEntity>>

    fun postUserSignIn(
        email: String,
        password: String
    ): Flow<Resource<UserSuccessEntity>>

    fun getKweaItems(
        token: String
    ): Flow<Resource<List<KweaItemEntity>>>
}