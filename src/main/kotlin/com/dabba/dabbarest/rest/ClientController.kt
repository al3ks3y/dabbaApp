package com.dabba.dabbarest.rest

import com.dabba.dabbarest.dto.ClientInDto
import com.dabba.dabbarest.dto.ClientOutDto
import com.dabba.dabbarest.model.Client
import com.dabba.dabbarest.service.ClientService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("client")
class ClientController (private val clientService: ClientService){
    @GetMapping
    @ApiOperation("Получение списка всех клиентов")
    fun getAll(): List<ClientOutDto> = clientService.all().map{it.toDto()}
    @PostMapping
    @ApiOperation("Добавление нового клиента в БД")
    @ResponseStatus(HttpStatus.CREATED)
    fun addClient(@RequestBody clientInDto: ClientInDto) =clientService.add(Client.fromDto(clientInDto))
}