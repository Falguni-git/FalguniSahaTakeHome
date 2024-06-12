package com.takehome.falgunisahatakehome

import com.takehome.falgunisahatakehome.repository.UserRepository
import com.takehome.falgunisahatakehome.viewmodel.MainViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.doNothing

class MainViewModelTest {

    private val userRepository = mock<UserRepository>()

    private val viewModel = mock<MainViewModel>()

    @Test
    fun  fetchUserInformationTest() = runBlocking {
         doNothing().`when`(userRepository).fetchUserInformation(anyString())
         viewModel.getUserData(anyString())
         verify(userRepository,times(0)).fetchUserInformation(anyString())
    }

    @Test
    fun fetchRepoTest() = runBlocking {
        doNothing().`when`(userRepository).fetchUserRepo(anyString())
        viewModel.getRepoList(anyString())
        verify(userRepository,times(0)).fetchUserRepo(anyString())
    }
}