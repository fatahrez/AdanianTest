package com.fatahapps.domain.usecases

import com.fatahapps.domain.entities.Resource
import com.fatahapps.domain.entities.UserEntity
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
class PostUserRegistrationTaskTest {
    private lateinit var postUserRegistrationTask: PostUserRegistrationTask

    val testUser = UserEntity(
        "user80",
        "user80",
        "user80@email.com",
        "123456",
        "123456",
        "0123456789"
    )
    @Mock
    private lateinit var ecobbaRepository: EcobbaRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        postUserRegistrationTask = PostUserRegistrationTask(
            ecobbaRepository
        )
    }

    @Test
    fun `post user registration starts with loading RETURNS Resource Loading`() = runBlocking {
        val userSuccess = TestDataGenerator.generateUserSuccessData()

        Mockito.`when`(ecobbaRepository.postUserRegistration(testUser))
            .thenReturn(
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(userSuccess))
                }
            )

        val result = postUserRegistrationTask(testUser).first()

        assert(result is Resource.Loading)
    }


}