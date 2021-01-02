package com.gokhana.demo.model

import javax.validation.constraints.NotEmpty

data class UserDTO(
    val id: Long,
    @field:NotEmpty
    val name: String,
    @field:NotEmpty
    val username: String
)

