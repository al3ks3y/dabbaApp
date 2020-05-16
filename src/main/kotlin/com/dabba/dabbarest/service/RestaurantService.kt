package com.dabba.dabbarest.service

import com.dabba.dabbarest.dao.RestaurantDao
import com.dabba.dabbarest.dto.DishInDto
import com.dabba.dabbarest.dto.ExtraInDto
import com.dabba.dabbarest.dto.RestaurantInDto
import com.dabba.dabbarest.dto.RestaurantOutDto
import com.dabba.dabbarest.model.Dish
import com.dabba.dabbarest.model.Extra
import com.dabba.dabbarest.model.KitchenType
import com.dabba.dabbarest.model.Restaurant
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class RestaurantService(private val restaurantDao: RestaurantDao) {
    fun findByName(name: String): MutableList<RestaurantOutDto> = restaurantDao.findByName(name.toUpperCase()).map { it.toDto() }.toMutableList()
    fun getAll(): MutableList<RestaurantOutDto> {
        val restaurantList = restaurantDao.findAll()
        return restaurantList.map { it.toDto() }.toMutableList()
    }

    fun add(restaurant: RestaurantInDto) = restaurantDao.save(Restaurant.fromDto(restaurant))
    fun addDish(dishInDto: DishInDto) {
        val restaurant:Restaurant= restaurantDao.findById(dishInDto.restaurantId).orElseThrow{NotFoundException("Ресторан с таким id не найден")}
        restaurant.addDish(Dish.fromDto(dishInDto))
        restaurantDao.save(restaurant)
    }

    fun addExtra(extraInDto: ExtraInDto) {
        val dish = restaurantDao.findAll().filter { it.dishes.filter { dish -> dish.id == extraInDto.dishId }.size == 1 }[0].getDish(extraInDto.dishId)
        dish?.addExtra(Extra.fromDto(extraInDto))
        val restaurant: Restaurant = restaurantDao.findAll().filter { it.dishes.contains(dish) }[0]
        restaurantDao.save(restaurant)
    }

    fun findDishById(dishId: Long): Dish? = restaurantDao.findAll().filter { it.dishes.filter { dish -> dish.id == dishId }.size == 1 }[0].getDish(dishId)
    fun deleteDishById(id: Long) {
    }
    fun findById(id:Long): Optional<Restaurant> =restaurantDao.findById(id)
    fun deleteRestaurant(id:Long)=restaurantDao.delete(findById(id).orElseThrow{NotFoundException("Ресторан с таким id не найден")})

    fun initTestDb() {
        val pjons = Restaurant(
                "Папа Джонс",
                "ул. Илона Маска, д. 9",
                KitchenType.EUROPEAN,
                "9:00",
                "23:00",
                "+7(495)123-12-34",
                "https://nsk.zakazaka.ru/db/674/587/org703.jpg",
                "order@papajohns.ru",
                20.0
        )
        restaurantDao.save(pjons)
        val pjonsid = findByName("Папа")[0].id!!
        addDish(DishInDto(pjonsid,
                "Пицца Маргарита",
                "https://cdn.dodostatic.net/static/Img/Products/Pizza/ru-RU/2869bdc5-4370-4703-8a84-871b8bc66d60.jpg",
                300, 450,
                "Итальянские травы, томатный соус, моцарелла, томаты"))
        addDish(DishInDto(pjonsid,
                "Пицца Мексиканская",
                "https://cdn.dodostatic.net/static/Img/Products/Pizza/ru-RU/ecd9d5b3-0cfc-4138-9559-18d9631fe8aa.jpg",
                380, 550,
                "Цыпленок, томатный соус, сладкий перец, красный лук, моцарелла, острый халапеньо, томаты, соус сальса"))
        addDish(DishInDto(pjonsid,
                "Пицца Пепперони",
                "https://cdn.dodostatic.net/static/Img/Products/Pizza/ru-RU/accc2ec9-5a93-4fb4-94bf-9006ce23fede.jpg",
                350, 520,
                "Пикантная пепперони, томатный соус, моцарелла"))
        addDish(DishInDto(pjonsid,
                "Пицца Овощи и грибы",
                "https://cdn.dodostatic.net/static/Img/Products/Pizza/ru-RU/743b12bc-5fbf-4872-8eaa-728b2709ccbf.jpg",
                410, 545,
                "Итальянские травы, томатный соус, кубики брынзы, шампиньоны, сладкий перец, красный лук, моцарелла, маслины, томаты"))
        val sushi = Restaurant(
                "Вкусные суши",
                "ул. Хаяо Миядзаки, д. 5",
                KitchenType.JAPANESE,
                "9:30",
                "22:30",
                "+7(495)123-12-34",
                "https://png.pngtree.com/templates/md/20180621/md_5b2bb63471cc0.jpg",
                "order@tastysushi.ru",
                4.5
        )
        restaurantDao.save(sushi)
        val sushiId = findByName("вкусные")[0].id!!
        addDish(DishInDto(sushiId,
                "Унаги ролл",
                "https://static.yakitoriya.ru/media/cache/9e/a0/9ea01d9e779cd505070c30c153d019a3.jpg",
                95, 110,
                "Рис для суси, угорь, васаби, кунжут, водоросли прессованные"))
        addDish(DishInDto(sushiId,
                "Тори унаги",
                "https://static.yakitoriya.ru/media/cache/ec/76/ec76a9c45aaa47e3380a8e6ee5ddee6c.jpg",
                85, 95,
                "Рис для суси, фарш (куриная грудка майонез, угорь, икра сельди), васаби, водоросли прессованные"))
        addDish(DishInDto(sushiId,
                "Вакамэ сарада",
                "https://static.yakitoriya.ru/media/cache/cf/b4/cfb4160204f0cf2b46e9cb5a5adc8bc7.jpg",
                100, 90,
                "Рис для суси, маринованные водоросли, васаби, водоросли прессованные"))
        addDish(DishInDto(sushiId,
                "Калифорния ролл",
                "https://static.yakitoriya.ru/media/cache/ce/3c/ce3c4e740ed0860816435da65f76bbcf.jpg",
                100, 90,
                "Рис для суси, крабовое мясо, икра летучей рыбы, майонез, огурцы, авокадо, васаби, водоросли прессованные (8 шт.)"))

        val tacoBell = Restaurant(
                "Тако Бэлл",
                "ул. Симона Боливара, д. 3",
                KitchenType.LATINO,
                "10:30",
                "22:00",
                "+7(495)123-12-34",
                "https://is2-ssl.mzstatic.com/image/thumb/Purple113/v4/e5/f6/7f/e5f67f5f-7dd1-f535-58aa-93a8216231a7/AppIcon-1x_U007emarketing-0-6-0-0-85-220.png/246x0w.png",
                "order@tacobell.ru",
                18.5
        )
        restaurantDao.save(tacoBell)
        val tacoId = findByName("Тако")[0].id!!
        addDish(DishInDto(tacoId,
                "Чикен тако",
                "https://www.thegardengrazer.com/wp-content/uploads/2019/10/easy-vegan-taco-crumbles-650-wm.jpg",
                195, 350,
                "Пшеничная тортилья, курица, специи (лук, чеснок, кумин, кориандр, куркума, сахар, яблоко, тамаринд), соус «Тако», соус «Чили», салат «Романо», сыр «Чеддер»"))
        addDish(DishInDto(tacoId,
                "Чикен буррито",
                "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fcdn-image.myrecipes.com%2Fsites%2Fdefault%2Ffiles%2Fstyles%2F4_3_horizontal_-_1200x900%2Fpublic%2Fchicken-fried-rice-burrito-dcms-large-image.jpg%3Fitok%3DLZ_UKbrX",
                195, 350,
                "Пшеничная тортилья, курица, специи (лук, чеснок, кумин, кориандр, куркума, сахар, яблоко, тамаринд), рис, соус «Тако», соус «Чили», сыр «Чеддер»"))

    }
}