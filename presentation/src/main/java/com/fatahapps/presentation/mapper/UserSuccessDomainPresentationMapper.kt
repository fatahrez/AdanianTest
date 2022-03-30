package com.fatahapps.presentation.mapper

import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.model.UserSuccess
import javax.inject.Inject

class UserSuccessDomainPresentationMapper @Inject constructor(): Mapper<UserSuccessEntity, UserSuccess> {
    override fun from(e: UserSuccess): UserSuccessEntity {
        return UserSuccessEntity(
            success = e.success,
            message = e.message,
            user = UserEntity(
                e.user.name,
                e.user.otherName,
                e.user.email,
                e.user.password,
                e.user.passwordConfirmation,
                e.user.msisdn
            ),
            token = e.token
        )
    }

    override fun to(t: UserSuccessEntity): UserSuccess {
        return UserSuccess(
            success = t.success,
            message = t.message,
            user = User(
                t.user.name,
                t.user.otherName,
                t.user.email,
                t.user.password,
                t.user.passwordConfirmation,
                t.user.msisdn
            ),
            token = t.token
        )
    }
}