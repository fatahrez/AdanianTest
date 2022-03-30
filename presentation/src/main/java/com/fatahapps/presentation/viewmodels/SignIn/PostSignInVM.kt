package com.fatahapps.presentation.viewmodels.SignIn

import androidx.lifecycle.ViewModel
import com.fatahapps.domain.usecases.PostUserSignInTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostSignInVM @Inject constructor(
    private val signInTask: PostUserSignInTask
): ViewModel(){

    fun postUserSign(
        email: String,
        password: String
    ) = signInTask(email, password)
}