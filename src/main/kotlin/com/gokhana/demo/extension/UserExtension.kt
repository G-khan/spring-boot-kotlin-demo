package com.gokhana.demo.extension

import com.gokhana.demo.entity.User
import com.gokhana.demo.model.UserDTO

fun User.toUserDTO() = UserDTO(
    name = this.name,
    username = this.username,
    id = this.id,
)

fun UserDTO.toUser() = User(
    name = this.name,
    username = this.username,
    id = this.id,
)