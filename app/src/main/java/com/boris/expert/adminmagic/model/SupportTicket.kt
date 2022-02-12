package com.boris.expert.adminmagic.model

import java.io.Serializable

data class SupportTicket(
    val id:String,
    val appName:String,
    val userName:String,
    val userId:String,
    val title:String,
    val message:String,
    val timeStamp:Long,
    var status:String,
    val lastReply:Long,
    val lastReplyBy:String
):Serializable{

    constructor():this("","","","","","",0,"",0,"")

}