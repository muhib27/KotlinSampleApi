package com.e.kotlinsampleapi

object FcmAdd {
    data class Result(val status: Status)
    data class Status(val code: String, val msg: String)
//    data class Status(val msg: String)
}