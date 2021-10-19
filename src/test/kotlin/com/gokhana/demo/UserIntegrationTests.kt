package com.gokhana.demo

import com.gokhana.demo.mock.mockUserDTO
import com.gokhana.demo.model.UserDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    @Order(1)
    fun `When retrieve user by username while user does not exists then user not found`() {
        val entity = restTemplate.getForEntity<String>("/users?username=X")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(2)
    fun `When create user while user is correct then create user successfully`() {
        val userDTO = UserDTO(1, "Gökhan", "G-khan")
        val entity = restTemplate.postForEntity<String>("/users", userDTO)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    @Order(2)
    fun `Create user while username is empty then bad request`() {
        val userDTO = mockUserDTO(username = "")
        val entity = restTemplate.postForEntity<String>("/users", userDTO)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    @Order(3)
    fun `When retrieve user by username while user is exists then user not found`() {
        val entity = restTemplate.getForEntity<String>("/users?username=G-khan")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

}