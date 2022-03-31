package com.fatahapps.presentation.viewmodels.kweas

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.usecases.GetKweaItemsTask
import com.fatahapps.presentation.mapper.KweaDomainPresentationMapper
import com.fatahapps.presentation.mapper.Mapper
import com.fatahapps.presentation.model.kweaModels.KweaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GetKweaItemsVM @Inject constructor(
    private val getKweaItemsTask: GetKweaItemsTask
): ViewModel(){

    fun showKweas(
        token: String
    ) = getKweaItemsTask(token)


}