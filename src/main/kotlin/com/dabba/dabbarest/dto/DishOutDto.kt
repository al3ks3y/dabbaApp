package com.dabba.dabbarest.dto

data class DishOutDto(
        val name: String,
        val pictureUrl: String?,
        val weigh: Int,
        val price: Int,
        val description: String,
        val comments: String?
)