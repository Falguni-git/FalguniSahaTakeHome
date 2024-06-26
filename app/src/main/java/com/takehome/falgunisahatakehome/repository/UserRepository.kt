package com.takehome.falgunisahatakehome.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.takehome.falgunisahatakehome.model.UserInfoResponse
import com.takehome.falgunisahatakehome.model.UserRepoResponse
import com.takehome.falgunisahatakehome.service.ApiConfigService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private val _UserData = MutableLiveData<UserInfoResponse>()
    val userData: LiveData<UserInfoResponse> get() = _UserData

    private val _UserRepo = MutableLiveData<List<UserRepoResponse>>()
    val userRepo: LiveData<List<UserRepoResponse>> get() = _UserRepo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    var errorMessage: String = ""
        private set

    /**
     * @method to fetch user repo information
     * @param userId is entered by the user
     */
     fun fetchUserInformation(userId: String) {
        _isLoading.value = true
        _isError.value = false

        val client = ApiConfigService.getApiService().getUserInfo(userId)

        // Send API request using Retrofit
        CoroutineScope(Dispatchers.IO).launch {
            client.enqueue(object : Callback<UserInfoResponse> {

                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    val responseBody = response.body()
                    if (!response.isSuccessful || responseBody == null) {
                        onError("Data Processing Error")
                        _isLoading.value = false
                        return
                    }
                    _isLoading.value = false
                    _UserData.postValue(responseBody)
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    onError(t.message)
                    t.printStackTrace()
                }

            })
        }
    }

    /**
     * @method to get the repo details for a particular repo
     * @param userId is entered by the user
     */
    fun fetchUserRepo(userId: String) {
        _isLoading.value = true
        _isError.value = false

        val client = ApiConfigService.getApiService().getUserRepo(userId)
        CoroutineScope(Dispatchers.IO).launch {
            // Send API request using Retrofit
            client.enqueue(object : Callback<List<UserRepoResponse>> {

                override fun onResponse(
                    call: Call<List<UserRepoResponse>>,
                    response: Response<List<UserRepoResponse>>
                ) {
                    val responseBody = response.body()

                    if (!response.isSuccessful || responseBody == null) {
                        onError("Data Processing Error")
                        _isLoading.value = false
                        return
                    }
                    _isLoading.value = false
                    _UserRepo.postValue(responseBody)
                }

                override fun onFailure(call: Call<List<UserRepoResponse>>, t: Throwable) {
                    onError(t.message)
                    t.printStackTrace()
                }
            })
        }
    }


    /**
     * @method to set the error message
     * @param inputMessage  is the error message
     */
    private fun onError(inputMessage: String?) {

        val message =
            if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
            else inputMessage

        errorMessage = StringBuilder("ERROR: ")
            .append("$message some data may not displayed properly").toString()

        _isError.value = true
        _isLoading.value = false
    }
}