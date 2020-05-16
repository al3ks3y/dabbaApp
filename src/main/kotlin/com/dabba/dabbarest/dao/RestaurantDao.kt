package com.dabba.dabbarest.dao

import com.dabba.dabbarest.model.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional


interface RestaurantDao:JpaRepository<Restaurant,Long> {
    @Query("SELECT r FROM Restaurant r WHERE upper(r.name) LIKE %:name%")
    fun findByName(@Param("name") name:String):MutableList<Restaurant>
}