package com.dabba.dabbarest.model

import com.dabba.dabbarest.dto.DishInDto
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@ApiModel
@Table(name = "restaurant")
data class Restaurant (
        @Id
        @JsonProperty("id")
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0L,
        val name: String,
        val address:String,
        val kitchenType: KitchenType,
        val openTime:LocalDateTime,
        val closeTime:LocalDateTime,
        val contactPhone:String,
        val logoUrl:String,
        val email:String,
        val serviceRadius:Double,
        @OneToMany(cascade = [CascadeType.ALL])
        val dishes:MutableList<Dish> =Collections.emptyList()
)
{
    fun addDish(dish: Dish):Unit {
        dish.restaurant=this
        this.dishes.add(dish)
    }
}

@ApiModel
@Entity
data class Dish (
        @ManyToOne ()
        @JoinColumn (name = "restaurantid")
        var restaurant: Restaurant?,
        @Id
        @JsonProperty("id")
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Long = 0L,
        val name:String,
        val pictureUrl:String?,
        val weigh:Int,
        val price:Int,
        val description:String,
        val comments:String
){
    companion object{
        fun fromDto(dishInDto: DishInDto):Dish= Dish(
                description = dishInDto.description,
                name = dishInDto.name,
                pictureUrl = dishInDto.pictureUrl,
                weigh = dishInDto.weigh,
                comments = dishInDto.comments,
                price = dishInDto.price,
                restaurant = null
        )
    }
}
@ApiModel
enum class KitchenType {
    RUSSIAN,
    CHINESE,
    JAPANESE,
    EUROPEAN,
    AMERICAN
}
