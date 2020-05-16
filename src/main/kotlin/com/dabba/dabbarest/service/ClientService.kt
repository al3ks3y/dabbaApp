package com.dabba.dabbarest.service

import com.dabba.dabbarest.dao.ClientDao
import com.dabba.dabbarest.model.Client
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ClientService (private val clientDao: ClientDao) {
    fun all():MutableList<Client> = clientDao.findAll()
    fun add(client : Client) = clientDao.save(client)
    fun get(id: Long): Optional<Client> = clientDao.findById(id)
    fun remove(id:Long):Unit= clientDao.deleteById(id)


}