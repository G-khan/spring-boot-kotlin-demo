package com.gokhana.demo

import com.gokhana.demo.entity.User
import com.gokhana.demo.model.toUserDTO
import com.gokhana.demo.repository.UserRepository
import com.gokhana.demo.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest(@Autowired val userService: UserService) {

    @MockBean
    lateinit var userRepository: UserRepository

    @Test
    fun `save user to repository`() {
        val user = User(1, "Gökhan", "G-khan")
        val userDTO = user.toUserDTO()
        Mockito.`when`(userRepository.save(user)).thenReturn(user)
        val savedUserDTO = userService.createUser(userDTO)
        assertThat(savedUserDTO).isEqualTo(userDTO)
    }

    @Test
    fun `retrieve saved user from repository`() {
        val user = User(1, "Gökhan", "G-khan")
        val userDTO = user.toUserDTO()
        Mockito.`when`(userRepository.findByUsername(user.username)).thenReturn(user)
        val savedUserDTO = userService.retrieveUser(user.username)
        assertThat(savedUserDTO).isEqualTo(userDTO)
    }

    @Test
    fun `retrieve not exist user from repository`() {
        val user = User(1, "Gökhan", "G-khan")
        Mockito.`when`(userRepository.findByUsername(user.username)).thenReturn(null)
        val savedUserDTO = userService.retrieveUser(user.username)
        assertThat(savedUserDTO).isNull()
    }

}