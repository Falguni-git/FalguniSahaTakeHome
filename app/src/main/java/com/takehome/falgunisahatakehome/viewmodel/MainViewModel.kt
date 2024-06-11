package com.takehome.falgunisahatakehome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takehome.falgunisahatakehome.model.DetailScreenModel
import com.takehome.falgunisahatakehome.repository.UserRepository

class MainViewModel() : ViewModel() {
    private val userRepository = UserRepository()
    private val _userDetail = MutableLiveData<DetailScreenModel>()
    val userDetail: LiveData<DetailScreenModel> get() = _userDetail

    /**
     * @method to fetch user repo information
     * @param userId is entered by the user
     */
    fun getUserData(userId: String) {
        userRepository.fetchUserInformation(userId)
    }

    /**
     * @method to get the repo details for a particular repo
     * @param userId is entered by the user
     */
    fun getRepoList(userId: String) {
        userRepository.fetchUserRepo(userId)
    }

    /**
     * @method to send the click events to open the details screen
     * @param detailScreen contains the data to be shown on the details screen
     */
    fun sendClickEvents(detailScreen: DetailScreenModel) {
        _userDetail.postValue(detailScreen)
    }

    fun getRepositoryInstance() : UserRepository = userRepository
}

