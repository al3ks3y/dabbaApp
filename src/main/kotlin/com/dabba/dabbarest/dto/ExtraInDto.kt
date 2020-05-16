package com.dabba.dabbarest.dto

import io.swagger.annotations.ApiModel

@ApiModel
data class ExtraInDto(
        val name: String,
        val price: Int,
        val dishId: Long
)