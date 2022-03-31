package com.fatahapps.data.mappers

import com.fatahapps.data.remote.dto.UserDTO
import com.fatahapps.data.remote.dto.UserSuccessDTO
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import javax.inject.Inject

class UserSuccessDomainDataMapper @Inject constructor(): Mapper<UserSuccessEntity, UserSuccessDTO> {
    override fun from(e: UserSuccessDTO): UserSuccessEntity {
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
            token = e.token,
            errors = e.errors
        )
    }

    override fun to(t: UserSuccessEntity): UserSuccessDTO {
        return UserSuccessDTO(
            success = t.success,
            message = t.message,
            user = UserDTO(
                t.user.name,
                t.user.otherName,
                t.user.email,
                t.user.password,
                t.user.passwordConfirmation,
                t.user.msisdn
            ),
            token = t.token,
            errors = t.errors
        )
    }
}