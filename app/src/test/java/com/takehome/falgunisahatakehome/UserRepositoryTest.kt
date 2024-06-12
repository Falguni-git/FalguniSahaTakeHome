package com.takehome.falgunisahatakehome

import com.takehome.falgunisahatakehome.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doNothing

class UserRepositoryTest {
    private val userRepository = Mockito.mock<UserRepository>()

    @Test
    fun getUserInformationTest() = runBlocking {
        doNothing().`when`(userRepository).fetchUserInformation(Mockito.anyString())
        Mockito.verify(userRepository, Mockito.times(0)).fetchUserInformation(Mockito.anyString())
    }
    @Test
    fun getUserRepoTest() = runBlocking {
        doNothing().`when`(userRepository).fetchUserRepo(Mockito.anyString())
        Mockito.verify(userRepository, Mockito.times(0)).fetchUserRepo(Mockito.anyString())
    }
}