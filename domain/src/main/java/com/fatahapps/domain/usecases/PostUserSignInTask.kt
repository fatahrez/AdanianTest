package com.fatahapps.domain.usecases

import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.domain.repository.EcobbaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostUserSignInTask @Inject constructor(
    private val repository: EcobbaRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<UserSuccessEntity>> {
        if (email.isBlank() || password.isBlank()) {
            return flow {  }
        }

        return repository.postUserSignIn(email, password)
    }
}