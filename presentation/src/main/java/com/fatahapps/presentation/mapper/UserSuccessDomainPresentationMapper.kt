package com.fatahapps.presentation.mapper

import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.model.UserSuccess
import javax.inject.Inject

class UserSuccessDomainPresentationMapper @Inject constructor(): Mapper<UserSuccessEntity, UserSuccess> {
    override fun from(e: UserSuccess): UserSuccessEntity {
        return UserSuccessEntity(
            e.success,
            e.message,
            UserEntity(
                e.user.name,
                e.user.otherName,
                e.user.email,
                e.user.password,
                e.user.passwordConfirmation,
                e.user.msisdn
            ),
            e.token,
            e.errors
        )
    }

    override fun to(t: UserSuccessEntity): UserSuccess {
        return UserSuccess(
            t.success,
            t.message,
            User(
                t.user.name,
                t.user.otherName,
                t.user.email,
                t.user.password,
                t.user.passwordConfirmation,
                t.user.msisdn
            ),
            t.token,
            t.errors
        )
    }

}