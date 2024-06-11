package com.takehome.falgunisahatakehome.model

import com.google.gson.annotations.SerializedName

data class UserInfoResponse (
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,
)
