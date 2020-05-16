package com.dabba.dabbarest.service

import com.dabba.dabbarest.dao.RestaurantDao
import com.dabba.dabbarest.dto.DishInDto
import com.dabba.dabbarest.model.Dish
import com.dabba.dabbarest.model.Restaurant
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class RestaurantService(private val restaurantDao: RestaurantDao) {
    fun findByName(name:String):MutableList<Restaurant> = restaurantDao.findByName(name.toUpperCase())
    fun getAll():MutableList<Restaurant> =restaurantDao.findAll()
    fun add(restaurant: Restaurant) = restaurantDao.save(restaurant)
    fun addDish(dishInDto: DishInDto) {
        val restaurant:Restaurant= restaurantDao.findById(dishInDto.restaurantId).orElseThrow{NotFoundException("Ресторан с таким id не найден")}
        restaurant.addDish(Dish.fromDto(dishInDto))
        restaurantDao.save(restaurant)
    }
    fun findById(id:Long): Optional<Restaurant> =restaurantDao.findById(id)
    fun deleteRestaurant(id:Long)=restaurantDao.delete(findById(id).orElseThrow{NotFoundException("Ресторан с таким id не найден")})
}