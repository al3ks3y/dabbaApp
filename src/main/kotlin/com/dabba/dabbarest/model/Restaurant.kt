package com.dabba.dabbarest.model

import com.dabba.dabbarest.dto.*
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import lombok.NoArgsConstructor
import org.hibernate.validator.constraints.Length
import javax.persistence.*

@Entity
@ApiModel
@NoArgsConstructor
@Table(name = "restaurant")
data class Restaurant (
        @Id
        @JsonProperty("id")
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,
        var name: String,
        var address: String,
        var kitchenType: KitchenType,
        var openTime: String,
        var closeTime: String,
        var contactPhone: String,
        var logoUrl: String?,
        var email: String,
        var serviceRadius: Double,
        @OneToMany(cascade = [CascadeType.ALL])
        var dishes: MutableList<Dish> = mutableListOf()
)
{
    fun addDish(dish: Dish):Unit {
        dish.restaurant=this
        this.dishes.add(dish)
    }

    fun getDish(id: Long): Dish? {
        if (this.dishes.filter { it.id == id }.size == 1) {
            return this.dishes.filter { it.id == id }[0]
        } else return null
    }

    fun toDto(): RestaurantOutDto = RestaurantOutDto(
            id = this.id,
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

    constructor(name: String, address: String, kitchenType: KitchenType, openTime: String, closeTime: String, contactPhone: String, logoUrl: String?, email: String, serviceRadius: Double)
            : this(0, "", "", KitchenType.RUSSIAN, "", "", "", "", "", 1.0, mutableListOf()) {
        this.name = name
        this.address = address
        this.kitchenType = kitchenType
        this.openTime = openTime
        this.closeTime = closeTime
        this.contactPhone = contactPhone
        this.logoUrl = logoUrl
        this.email = email
        this.serviceRadius = serviceRadius
    }
    companion object {
        fun fromDto(dto: RestaurantInDto, fileName: String?): Restaurant = Restaurant(
                name = dto.name,
                address = dto.address,
                openTime = dto.openTime,
                closeTime = dto.closeTime,
                contactPhone = dto.contactPhone,
                email = dto.email,
                serviceRadius = dto.serviceRadius,
                logoUrl = fileName,
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
        internal val id: Long? = 0L,
        val name:String,
        @Length(max = 800)
        val pictureUrl:String?,
        val weigh:Int,
        val price:Int,
        val description:String,
        @OneToMany(cascade = [CascadeType.ALL])
        var extras: MutableList<Extra> = mutableListOf()
){
    companion object{
        fun fromDto(dishInDto: DishInDto, fileName: String?): Dish = Dish(
                description = dishInDto.description,
                name = dishInDto.name,
                pictureUrl = fileName,
                weigh = dishInDto.weigh,
                price = dishInDto.price,
                restaurant = null
        )
    }

    fun toDto(): DishOutDto = DishOutDto(
            id = this.id,
            name = this.name,
            description = this.description,
            pictureUrl = this.pictureUrl,
            price = this.price,
            weigh = this.weigh,
            extras = this.extras.map { it.toDto() }.toMutableList()
    )

    fun addExtra(extra: Extra): Unit {
        extra.dish = this
        this.extras.add(extra)
    }
}

@ApiModel
@Entity
data class Extra(
        @Id
        @JsonProperty("id")
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Long? = 0L,
        @ManyToOne
        @JoinColumn(name = "dishid")
        var dish: Dish?,
        var name: String,
        var price: Int,
        var status: Boolean
) {
    fun toDto() = ExtraOutDto(id, name, price, status)

    companion object {
        fun fromDto(extraInDto: ExtraInDto) = Extra(null, null, extraInDto.name, extraInDto.price, false)
    }
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
