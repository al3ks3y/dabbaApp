package com.dabba.dabbarest.service

import com.dabba.dabbarest.dao.ClientDao
import com.dabba.dabbarest.manager.FirebaseManager
import com.dabba.dabbarest.model.Client
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ClientService (private val clientDao: ClientDao, private val firebaseManager: FirebaseManager) {
    fun all():MutableList<Client> = clientDao.findAll()
    fun add(client : Client) = clientDao.save(client)
    fun get(id: Long): Optional<Client> = clientDao.findById(id)
    fun remove(id:Long):Unit= clientDao.deleteById(id)

}