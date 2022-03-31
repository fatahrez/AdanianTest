package com.fatahapps.adaniantest.di

import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.presentation.mapper.KweaDomainPresentationMapper
import com.fatahapps.presentation.mapper.Mapper
import com.fatahapps.presentation.mapper.UserDomainPresentationMapper
import com.fatahapps.presentation.mapper.UserSuccessDomainPresentationMapper
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.model.UserSuccess
import com.fatahapps.presentation.model.kweaModels.KweaItem
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module()
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindsUserMapper(
        userDomainPresentationMapper: UserDomainPresentationMapper
    ): Mapper<UserEntity, User>

    @Binds
    abstract fun bindsUserSuccessMapper(
        userSuccessDomainPresentationMapper: UserSuccessDomainPresentationMapper
    ): Mapper<UserSuccessEntity, UserSuccess>

    @Binds
    abstract fun bindsKweaMapper(
        kweaDomainPresentationMapper: KweaDomainPresentationMapper
    ): Mapper<KweaItemEntity, KweaItem>
}