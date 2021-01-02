package com.gokhana.demo.service

import com.gokhana.demo.entity.User
import com.gokhana.demo.model.UserDTO
import com.gokhana.demo.model.toUser
import com.gokhana.demo.model.toUserDTO

interface UserService {
    fun saveUser(user: User): User
    fun getUser(username: String): User?

    fun createUser(userDTO: UserDTO): User {
        return saveUser(userDTO.toUser())
    }

    fun retrieveUser(username:String) : UserDTO? {
        return getUser(username)?.toUserDTO()
    }


}