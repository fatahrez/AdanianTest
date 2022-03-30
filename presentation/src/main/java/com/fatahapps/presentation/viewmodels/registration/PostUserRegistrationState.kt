package com.fatahapps.presentation.viewmodels.registration

import com.fatahapps.presentation.model.UserSuccess

data class PostUserRegistrationState(
    val success: UserSuccess? = null,
    val isLoading: Boolean = false
)