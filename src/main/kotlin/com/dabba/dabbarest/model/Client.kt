package com.dabba.dabbarest.model

import com.dabba.dabbarest.dto.ClientInDto
import com.dabba.dabbarest.dto.ClientOutDto
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name="client")
data class Client (
        @Id
        @JsonProperty("id")
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        val name:String,
        val surname:String,
        val address:String,
        val apartment:String,
        val entrance:Int?,
        val floor:Int?,
        val currentLocation:String?

)
{
        fun toDto(): ClientOutDto = ClientOutDto(
                id=this.id!!,
                name = this.name,
                surname = this.surname,
                address = this.address,
                apartment = this.apartment,
                entrance = this.entrance,
                floor = this.floor,
                currentLocation = this.currentLocation
        )
        companion object{
                fun fromDto(dto: ClientInDto) =Client(
                        name=dto.name,
                        surname = dto.surname,
                        address = dto.address,
                        apartment = dto.apartment,
                        entrance = dto.entrance,
                        floor = dto.floor,
                        currentLocation = dto.currentLocation
                )
        }
}