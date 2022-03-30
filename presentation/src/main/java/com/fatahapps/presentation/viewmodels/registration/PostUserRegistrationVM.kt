package com.fatahapps.presentation.viewmodels.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.domain.usecases.PostUserRegistrationTask
import com.fatahapps.presentation.mapper.Mapper
import com.fatahapps.presentation.mapper.UserSuccessDomainPresentationMapper
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.model.UserSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PostUserRegistrationVM @Inject constructor(
    private val postUserRegistrationTask: PostUserRegistrationTask,
    private val userMapper: Mapper<UserEntity, User>,
    private val userSuccessMapper: Mapper<UserSuccessEntity, UserSuccess>
): ViewModel(){

    private val _state = MutableStateFlow(PostUserRegistrationState())
    val state: StateFlow<PostUserRegistrationState> = _state.asStateFlow()

    fun postUserRegistration(
        user: User
    ) = postUserRegistrationTask(userMapper.from(user))
}