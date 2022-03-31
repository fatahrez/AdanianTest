package com.fatahapps.domain.usecases

import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.domain.repository.EcobbaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUserRegistrationTask @Inject constructor(
    private val repository: EcobbaRepository
) {
    operator fun invoke(user: UserEntity): Flow<Resource<UserSuccessEntity>> {
        return repository.postUserRegistration(user)
}