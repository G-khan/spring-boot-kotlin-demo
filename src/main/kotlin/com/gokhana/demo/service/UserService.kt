package com.gokhana.demo.service

import com.gokhana.demo.model.UserDTO

interface UserService {
	fun createUser(userDTO: UserDTO): UserDTO
	fun getUser(username: String): UserDTO?
}