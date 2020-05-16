package com.dabba.dabbarest.dto

import com.dabba.dabbarest.model.KitchenType

data class RestaurantOutDto(
        val id: Long?,
        val name: String,
        val address: String,
        val kitchenType: KitchenType,
        val openTime: String,
        val closeTime: String,
        val contactPhone: String,
        val logoUrl: String,
        val email: String,
        val serviceRadius: Double,
        val dishes: MutableList<DishOutDto>
)