package com.dabba.dabbarest.model

import com.dabba.dabbarest.dto.DishInDto
import com.dabba.dabbarest.dto.DishOutDto
import com.dabba.dabbarest.dto.RestaurantInDto
import com.dabba.dabbarest.dto.RestaurantOutDto
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import org.hibernate.validator.constraints.Length
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
        val openTime: String,
        val closeTime: String,
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

    fun toDto(): RestaurantOutDto = RestaurantOutDto(
            name = this.name,
            address = this.address,
            kitchenType = this.kitchenType,
            openTime = this.openTime,
            closeTime = this.closeTime,
            contactPhone = this.contactPhone,
            dishes = this.dishes.map { it.toDto() }.toMutableList(),
            email = this.email,
            serviceRadius = this.serviceRadius,
            logoUrl = this.logoUrl
    )

    companion object {
        fun fromDto(dto: RestaurantInDto): Restaurant = Restaurant(
                name = dto.name,
                address = dto.address,
                openTime = dto.openTime,
                closeTime = dto.closeTime,
                contactPhone = dto.contactPhone,
                email = dto.email,
                serviceRadius = dto.serviceRadius,
                logoUrl = dto.logoUrl,
                kitchenType = KitchenType.fromString(dto.kitchenType)
        )
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
        @Length(max = 800)
        val pictureUrl:String?,
        val weigh:Int,
        val price:Int,
        val description:String,
        val comments: String?
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

    fun toDto(): DishOutDto = DishOutDto(
            name = this.name,
            description = this.description,
            comments = this.comments,
            pictureUrl = this.pictureUrl,
            price = this.price,
            weigh = this.weigh
    )


}
@ApiModel
enum class KitchenType {
    RUSSIAN,
    CHINESE,
    JAPANESE,
    EUROPEAN,
    AMERICAN,
    VIETNAMESE,
    LATINO;

    companion object {
        fun fromString(string: String): KitchenType {
            for (kitchentype in values()) {
                if (string == kitchentype.toString()) {
                    return kitchentype
                }
            }
            return RUSSIAN
        }
    }
}
