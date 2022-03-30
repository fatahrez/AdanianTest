package com.fatahapps.presentation.mapper

import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.presentation.model.User
import javax.inject.Inject

class UserDomainPresentationMapper @Inject constructor(): Mapper<UserEntity, User> {
    override fun from(e: User): UserEntity {
        return UserEntity(
            name = e.name,
            otherName = e.otherName,
            email = e.email,
            password = e.password,
            passwordConfirmation = e.passwordConfirmation,
            msisdn = e.msisdn
        )
    }

    override fun to(t: UserEntity): User {
        return User(
            name = t.name,
            otherName = t.otherName,
            email = t.email,
            password = t.password,
            passwordConfirmation = t.passwordConfirmation,
            msisdn = t.msisdn
        )
    }
}