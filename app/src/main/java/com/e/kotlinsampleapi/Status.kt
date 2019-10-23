package com.e.kotlinsampleapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Status{
    @SerializedName("status")
    @Expose
    private var status: CommonStatus? = null

}