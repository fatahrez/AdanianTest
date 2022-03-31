package com.fatahapps.domain.usecases

import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.repository.EcobbaRepository
import com.fatahapps.domain.util.TestDataGenerator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class PostUserSignInTaskTest {
    private lateinit var postUserSignInTask: PostUserSignInTask

    @Mock
    private lateinit var ecobbaRepository: EcobbaRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        postUserSignInTask = PostUserSignInTask(
            ecobbaRepository
        )
    }

    @Test
    fun `post user sign in starts with loading RETURNS Resource Loading`() = runBlocking {
        val email = "user80@email.com"
        val password = "123456"
        val userSuccess = TestDataGenerator.generateUserSuccessData()

        Mockito.`when`(ecobbaRepository.postUserSignIn(email, password))
            .thenReturn(
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(userSuccess))
                }
            )

        val result = postUserSignInTask(email, password).first()

        assert((result is Resource.Loading))
    }

    @Test
    fun `post user sign in successfully RETURNS Resource Success + Data`() = runBlocking {
        val email = "user80@email.com"
        val password = "123456"
        val userSuccess = TestDataGenerator.generateUserSuccessData()

        Mockito.`when`(ecobbaRepository.postUserSignIn(email, password))
            .thenReturn(
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(userSuccess))
                }
            )

        val result = postUserSignInTask(email, password).last()

        assert(result is Resource.Success && (result.data?.success ?: true))
    }

    @Test
    fun `post user sign in failure RETURNS null + Error`() = runBlocking {
        val email = "user80@email.com"
        val password = "123456"
        val userError = TestDataGenerator.generateUserFailDataSignIn()

        Mockito.`when`(ecobbaRepository.postUserSignIn(email, password))
            .thenReturn(
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Error(userError.message))
                }
            )

        val result = postUserSignInTask(email, password).last()

        assert(result is Resource.Error)
    }
}