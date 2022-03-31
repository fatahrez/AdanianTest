package com.fatahapps.domain.usecases

import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.repository.EcobbaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetKweaItemsTask @Inject constructor(
    private val repository: EcobbaRepository
) {
    operator fun invoke(token: String): Flow<Resource<List<KweaItemEntity>>> {
        if (token == null) {
            return flow{ }
        }
        return repository.getKweaItems(token)
    }
}