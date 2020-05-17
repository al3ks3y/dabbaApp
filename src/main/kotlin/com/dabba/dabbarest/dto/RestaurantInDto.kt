package com.dabba.dabbarest.dto

import io.swagger.annotations.ApiModel

@ApiModel("Объект для создания ресторана")
data class RestaurantInDto(
        val name: String,
        val address: String,
        val kitchenType: String,
        val openTime: String,
        val closeTime: String,
        val contactPhone: String,
        val logoUrl: String?,
        val email: String,
        val serviceRadius: Double
)