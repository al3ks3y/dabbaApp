package com.dabba.dabbarest.dto

import io.swagger.annotations.ApiModel

@ApiModel("Описание блюда для добавления в ресторан")
data class DishInDto (
        val restaurantId:Long,
        val name:String,
        val pictureUrl:String?,
        val weigh:Int,
        val price:Int,
        val description:String,
        val comments:String
)