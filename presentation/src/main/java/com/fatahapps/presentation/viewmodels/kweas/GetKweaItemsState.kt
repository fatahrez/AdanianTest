package com.fatahapps.presentation.viewmodels.kweas

import com.fatahapps.domain.entities.KweaModels.KweaItemEntity

data class GetKweaItemsState (
    val items: List<KweaItemEntity>? = null,
    val isLoading: Boolean = false
)