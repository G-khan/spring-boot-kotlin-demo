package com.gokhana.demo.mock

import com.gokhana.demo.entity.User
import com.gokhana.demo.model.UserDTO

fun mockUser(
	id: Long = 1,
	name: String = "Gökhan",
	username: String = "G-khan"
) = User(id, name, username)


fun mockUserDTO(
	id: Long = 1,
	name: String = "Zühtü",
	username: String = "zuhtu"
) = UserDTO(id, name, username)