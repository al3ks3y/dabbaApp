package com.dabba.dabbarest.dto

import io.swagger.annotations.ApiModel
import org.springframework.web.multipart.MultipartFile

@ApiModel("Описание блюда для добавления в ресторан")
data class DishInDto (
        val restaurantId:Long,
        val name:String,
        val picture: MultipartFile?,
        val weigh:Int,
        val price:Int,
        val description: String
)