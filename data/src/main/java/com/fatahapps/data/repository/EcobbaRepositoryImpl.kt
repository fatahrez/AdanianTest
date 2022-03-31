package com.fatahapps.data.repository

import com.fatahapps.data.local.KweasDao
import com.fatahapps.data.local.models.kweaModels.KweaItemLocal
import com.fatahapps.data.mappers.KweaDomainLocalMapper
import com.fatahapps.data.mappers.KweaLocalDTOMapper
import com.fatahapps.data.mappers.Mapper
import com.fatahapps.data.remote.EcobbaApi
import com.fatahapps.data.remote.dto.UserDTO
import com.fatahapps.data.remote.dto.UserSuccessDTO
import com.fatahapps.data.remote.dto.kweaModels.KweaItemDTO
import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.domain.repository.EcobbaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EcobbaRepositoryImpl @Inject constructor(
    private val api: EcobbaApi,
    private val dao: KweasDao,
    private val userMapper: Mapper<UserEntity, UserDTO>,
    private val userSuccessMapper: Mapper<UserSuccessEntity, UserSuccessDTO>,
    private val kweaDomainLocalMapper: Mapper<KweaItemEntity, KweaItemLocal>,
    private val kweaLocalDTOMapper: Mapper<KweaItemLocal, KweaItemDTO>
): EcobbaRepository {
    override fun postUserRegistration(user: UserEntity): Flow<Resource<UserSuccessEntity>> = flow{
        emit(Resource.Loading())

        // Since we are not saving this data db no need for caching or single source of truth
        try {
            val remoteData = api.postUserRegistration(userMapper.to(user))
            emit(Resource.Success(userSuccessMapper.from(remoteData)))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong",
                data = null
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server check your internet connection",
                data = null
            ))
        }
    }

    override fun postUserSignIn(
        email: String,
        password: String
    ): Flow<Resource<UserSuccessEntity>> = flow {
        emit(Resource.Loading())

        // Since we are not saving this data db no need for caching or single source of truth

        try {
            val remoteData = api.postUserSignIn(email, password)
            emit(Resource.Success(userSuccessMapper.from(remoteData)))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong",
                data = null
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server check your internet connection",
                data = null
            ))
        }
    }

    override fun getKweaItems(token: String): Flow<Resource<List<KweaItemEntity>>> = flow {
        emit(Resource.Loading())

        val kweasDao = dao.getAllKweas().map {
            kweaDomainLocalMapper.from(it)

        }

        emit(Resource.Loading(data = kweasDao))

        try {
            val remoteKweas = api.getKweaItems(token)
            dao.deleteKweas()
            dao.insertKweas(remoteKweas.data.map {
                kweaLocalDTOMapper.from(it)
            })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = kweasDao
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server check your internet connection",
                data = kweasDao
            ))
        }

        // Get any data from cache - single source of truth
        val finalKweaDao = dao.getAllKweas().map {
            kweaDomainLocalMapper.from(it)
        }
        emit(Resource.Success(finalKweaDao))
    }
}