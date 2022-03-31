package com.fatahapps.presentation.viewmodels.kweas

import com.fatahapps.presentation.model.kweaModels.KweaItem

data class GetKweaItemsState (
    val items: List<KweaItem>? = null,
    val isLoading: Boolean = false
)