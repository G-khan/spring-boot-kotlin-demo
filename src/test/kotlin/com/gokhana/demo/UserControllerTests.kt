package com.gokhana.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.gokhana.demo.service.UserService
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.internal.matchers.Any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class UserControllerTests(
	@Autowired private val mockMvc: MockMvc
) {

	private val mapper = ObjectMapper()

	@MockBean
	lateinit var userService: UserService

	@Nested
	inner class UserCreation {
		@Test
		fun `When create user then user created with expected id`() {
			val userDTO = mockUserDTO()
			Mockito.`when`(userService.createUser(userDTO)).thenReturn(userDTO)
			mockMvc.perform(
				post("/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(userDTO))
					.accept(MediaType.APPLICATION_JSON)
			)
				.andExpect(status().isCreated)
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("id").value(userDTO.id))
		}
	}


	@Nested
	inner class UserRetrieve {

		@Test
		fun `when retrieve user while user is exists then user returns with expected id`() {
			val userDTO = mockUserDTO()
			Mockito.`when`(userService.getUser(userDTO.username)).thenReturn(userDTO)
			mockMvc.perform(
				get("/users").param("username", userDTO.username).contentType(MediaType.APPLICATION_JSON_VALUE)
					.accept(MediaType.APPLICATION_JSON)
			)
				.andExpect(status().isOk)
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("id").value(userDTO.id))
		}


		@Test
		fun `When retrieve user while user does not exists then user not found`() {
			Mockito.`when`(userService.getUser(Any.ANY.toString())).thenReturn(null)
			mockMvc.perform(get("/users").param("username", "G").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound)
		}
	}
}
