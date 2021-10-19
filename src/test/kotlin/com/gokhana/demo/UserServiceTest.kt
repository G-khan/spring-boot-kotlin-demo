package com.gokhana.demo

import com.gokhana.demo.extension.toUserDTO
import com.gokhana.demo.mock.mockUser
import com.gokhana.demo.repository.UserRepository
import com.gokhana.demo.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
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

	@Nested
	inner class UserCreation {
		@Test
		fun `save user to repository`() {
			val user = mockUser()
			val userDTO = user.toUserDTO()
			Mockito.`when`(userRepository.save(user)).thenReturn(user)
			val savedUserDTO = userService.createUser(userDTO)
			assertThat(savedUserDTO).isEqualTo(userDTO)
		}
	}



	@Nested
	inner class UserRetrieve {
		@Test
		fun `retrieve not exist user from repository`() {
			val user = mockUser(username = "Zuhtu")
			Mockito.`when`(userRepository.findByUsername(user.username)).thenReturn(null)
			val savedUserDTO = userService.getUser(user.username)
			assertThat(savedUserDTO).isNull()
		}

		@Test
		fun `retrieve saved user from repository`() {
			val user = mockUser()
			val userDTO = user.toUserDTO()
			Mockito.`when`(userRepository.findByUsername(user.username)).thenReturn(user)
			val savedUserDTO = userService.getUser(user.username)
			assertThat(savedUserDTO).isEqualTo(userDTO)
		}
	}

}