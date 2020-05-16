package com.dabba.dabbarest

import com.dabba.dabbarest.dao.ClientDao
import org.junit.Rule
import com.dabba.dabbarest.dao.RestaurantDao
import com.dabba.dabbarest.model.Client
import com.dabba.dabbarest.service.ClientService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.JUnitSoftAssertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase
@ComponentScan(basePackages = ["com.dabba.dabbarest.service","com.dabba.dabbarest.model","com.dabba.dabbarest.dao"])
class DabbaRestApplicationTests {
	@Autowired
	lateinit var clientDao: ClientDao
	@Autowired
	lateinit var clientService: ClientService
	@get:Rule
	var softly = JUnitSoftAssertions()
	@Test
	fun clientSaveSuccess() {
		clientDao.save(Client(1L,"Name", "Surname",
				"address","",1,1,null))
		val result=clientService.get(1L)
		softly.assertThat(result).isNotEmpty
		softly.assertThat(result.get().id).isNotNull
		softly.assertThat(result.get().address).isEqualTo("address")
	}


	@Test
	fun exampleTest(){
		softly.assertThat(1+2).isEqualTo(3)
	}

}
