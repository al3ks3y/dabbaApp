package com.dabba.dabbarest.dao

import com.dabba.dabbarest.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional


interface ClientDao : JpaRepository<Client, Long> {
}