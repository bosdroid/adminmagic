package com.boris.expert.adminmagic.model

import java.io.Serializable

data class Message(
    val id: String,
    val userId: String,
    val type:String,
    val message: String,
    val timeStamp: Long
) : Serializable {
    constructor() : this("", "","", "", 0)
}