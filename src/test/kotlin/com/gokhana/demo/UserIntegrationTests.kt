package com.gokhana.demo

import com.gokhana.demo.model.UserDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTests (@Autowired val restTemplate: TestRestTemplate) {

    @Test
    @Order(1)
    fun `Assert retrieve user by username while use does not exists in the system`() {
        val entity = restTemplate.getForEntity<String>("/users?user=X")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(2)
    fun `Assert create user by username while use does not exists in the system`() {
        val userDTO = UserDTO(1,"Gökhan","G-khan")
        val entity = restTemplate.postForEntity<String>("/users",userDTO)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.CREATED)
    }


    @Test
    @Order(2)
    fun `Assert create user with empty username while use does not exists in the system`() {
        val userDTO = UserDTO(1,"Gökhan","")
        val entity = restTemplate.postForEntity<String>("/users",userDTO)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    @Order(3)
    fun `Assert retrieve user by username while user  exist in the system`() {
        val entity = restTemplate.getForEntity<String>("/users?user=G-khan")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

}