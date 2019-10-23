package com.e.kotlinsampleapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommonStatus{
    @SerializedName("code")
    @Expose
    private var code: Int? = null
    @SerializedName("msg")
    @Expose
    private var msg: String? = null
}