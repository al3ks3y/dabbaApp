package com.dabba.dabbarest.dto

import io.swagger.annotations.ApiModel

@ApiModel
data class DishOutDto(
        val id: Long?,
        val name: String,
        val pictureUrl: String?,
        val weigh: Int,
        val price: Int,
        val description: String,
        val extras: MutableList<ExtraOutDto>
)