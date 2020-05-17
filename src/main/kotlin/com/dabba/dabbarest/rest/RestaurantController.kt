package com.dabba.dabbarest.rest

import com.dabba.dabbarest.dto.DishInDto
import com.dabba.dabbarest.dto.ExtraInDto
import com.dabba.dabbarest.dto.RestaurantInDto
import com.dabba.dabbarest.dto.RestaurantOutDto
import com.dabba.dabbarest.service.RestaurantService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("restaurant")
class RestaurantController (private val restaurantService: RestaurantService){
    @GetMapping
    @ApiOperation("Получение списка всех ресторанов")
    fun getAll(): MutableList<RestaurantOutDto> = restaurantService.getAll()
    @PostMapping
    @ApiOperation("Добавление нового ресторана в базу")
    fun add(@RequestBody restaurant: RestaurantInDto
            //,@RequestParam(value = "file",required = false) file: MultipartFile
    ) = restaurantService.add(restaurant, null)
    @GetMapping("/{name}")
    @ApiOperation("Поиск ресторана по названию. Независимо от регистра. Можно использовать в поисковой строке")
    fun findByName(@PathVariable("name") name:String) = restaurantService.findByName(name)
    @PostMapping("/dish")
    @ApiOperation("Добавить блюдо")
    fun addDish(@RequestBody dishInDto: DishInDto
            //, @RequestParam(value = "file",required = false) file: MultipartFile
    ) = restaurantService.addDish(dishInDto, null)
    @DeleteMapping
    @ApiOperation("Удалить ресторан по его id")
    fun deleteRestaurantById(id: Long) = restaurantService.deleteRestaurant(id)

    @DeleteMapping("/dish")
    @ApiOperation("Удалить блюдо по id(все еще в разработке)")
    fun deleteDishById(id: Long) = restaurantService.deleteDishById(id)

    @PostMapping("/extra")
    @ApiOperation("Добавить доп-опцию")
    fun addExtra(extraInDto: ExtraInDto) = restaurantService.addExtra(extraInDto)

    @PostMapping("/init")
    @ApiOperation("Инициировать базу данных с тестовыми записями")
    fun initTestDb() = restaurantService.initTestDb()

    @PostMapping("/file")
    @ApiOperation("Добавить файл")
    fun uploadfile(@RequestParam(value = "file", required = false) file: MultipartFile) = file.name
}