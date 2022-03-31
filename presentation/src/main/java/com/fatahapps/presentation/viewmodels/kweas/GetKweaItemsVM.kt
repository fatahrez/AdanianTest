package com.fatahapps.presentation.viewmodels.kweas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.usecases.GetKweaItemsTask
import com.fatahapps.presentation.mapper.KweaDomainPresentationMapper
import com.fatahapps.presentation.mapper.Mapper
import com.fatahapps.presentation.model.kweaModels.KweaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GetKweaItemsVM @Inject constructor(
    private val getKweaItemsTask: GetKweaItemsTask,
    private val kweaDomainPresentationMapper: Mapper<KweaItemEntity, KweaItem>
): ViewModel(){

    private val _state = MutableStateFlow(GetKweaItemsState())
    val state: StateFlow<GetKweaItemsState> = _state.asStateFlow()

    fun showKweas(token: String) {
        getKweaItemsTask(token)
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            items = result.data?.map {
                                kweaDomainPresentationMapper.to(it)
                            } ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            items = result.data?.map {
                                kweaDomainPresentationMapper.to(it)
                            } ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}