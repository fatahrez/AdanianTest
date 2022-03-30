package com.fatahapps.data.mappers

import com.fatahapps.data.remote.dto.UserDTO
import com.fatahapps.domain.entities.UserEntity
import javax.inject.Inject

class UserDomainDataMapper @Inject constructor(): Mapper<UserEntity, UserDTO> {
    override fun from(e: UserDTO): UserEntity {
        return UserEntity(
            name = e.name,
            otherName = e.otherName,
            email = e.email,
            password = e.password,
            passwordConfirmation = e.passwordConfirmation,
            msisdn = e.msisdn
        )
    }

    override fun to(t: UserEntity): UserDTO {
        return UserDTO(
            name = t.name,
            otherName = t.otherName,
            email = t.email,
            password = t.password,
            passwordConfirmation = t.passwordConfirmation,
            msisdn = t.msisdn
        )
    }

}