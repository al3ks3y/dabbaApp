package com.dabba.dabbarest.dto

import io.swagger.annotations.ApiModel

@ApiModel
data class ClientInDto (
        val name:String,
        val surname:String,
        val address:String,
        val apartment:String,
        val entrance:Int?,
        val floor:Int?,
        val currentLocation:String?
)