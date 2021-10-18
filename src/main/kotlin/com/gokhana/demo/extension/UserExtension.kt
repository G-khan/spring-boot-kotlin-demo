package com.gokhana.demo.model

import com.gokhana.demo.entity.User

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