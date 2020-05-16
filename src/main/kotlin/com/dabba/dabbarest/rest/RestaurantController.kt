package com.dabba.dabbarest.rest

import com.dabba.dabbarest.dto.DishInDto
import com.dabba.dabbarest.dto.RestaurantOutDto
import com.dabba.dabbarest.model.Restaurant
import com.dabba.dabbarest.service.RestaurantService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("restaurant")
class RestaurantController (private val restaurantService: RestaurantService){
    @GetMapping
    @ApiOperation("Получение списка всех ресторанов")
    fun getAll(): MutableList<RestaurantOutDto> = restaurantService.getAll()
    @PostMapping
    @ApiOperation("Добавление нового ресторана в базу")
    fun add(@RequestBody restaurant: Restaurant) =restaurantService.add(restaurant)
    @GetMapping("/{name}")
    @ApiOperation("Поиск ресторана по названию. Независимо от регистра. Можно использовать в поисковой строке")
    fun findByName(@PathVariable("name") name:String) = restaurantService.findByName(name)
    @PostMapping("/dish")
    @ApiOperation("Добавить блюдо")
    fun addDish(@RequestBody dishInDto: DishInDto) =restaurantService.addDish(dishInDto)
    @DeleteMapping
    @ApiOperation("Удалить ресторан по его id")
    fun deleteById(id:Long)=restaurantService.deleteRestaurant(id)

    @GetMapping("/init")
    @ApiOperation("Инициировать базу данных с тестовыми данными")
    fun initTestDb() = restaurantService.initTestDb()
}