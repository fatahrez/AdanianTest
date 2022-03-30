package com.fatahapps.adaniantest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.viewmodels.registration.PostUserRegistrationVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val postUserRegistrationVM: PostUserRegistrationVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user: User = User("user83", "user83", "user83@email.com", "userpass", "userpass", "0987654326")
        postUserRegistrationVM.postUserRegistration(user)
    }
}