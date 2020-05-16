package com.dabba.dabbarest.dto

import io.swagger.annotations.ApiModel

@ApiModel
data class ClientOutDto(
        val id: Long?,
        val name:String,
        val surname:String,
        val address:String,
        val apartment:String,
        val entrance:Int?,
        val floor:Int?,
        val currentLocation:String?
)
