package com.takehome.falgunisahatakehome.service


import com.takehome.falgunisahatakehome.model.UserRepoResponse
import com.takehome.falgunisahatakehome.model.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    // Get current weather data
    @GET("/users/{userId}")
    fun getUserInfo(@Path("userId") id: String): Call<UserInfoResponse>

    @GET("/users/{userId}/repos")
    fun getUserRepo(@Path("userId") id: String): Call<List<UserRepoResponse>>
}