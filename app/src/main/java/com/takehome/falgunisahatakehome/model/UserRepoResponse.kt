package com.takehome.falgunisahatakehome.model

import com.google.gson.annotations.SerializedName

data class UserRepoResponse(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt : String? = null,

    @field:SerializedName("stargazers_count")
    val stargazersCount: Int = 0,

    @field:SerializedName("forks")
    val forks: Int = 0
)

