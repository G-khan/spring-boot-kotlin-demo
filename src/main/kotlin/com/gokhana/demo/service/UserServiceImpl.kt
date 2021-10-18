package com.gokhana.demo.service

import com.gokhana.demo.model.UserDTO
import com.gokhana.demo.model.toUser
import com.gokhana.demo.model.toUserDTO
import com.gokhana.demo.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun createUser(userDTO: UserDTO): UserDTO {
        val user = userDTO.toUser()
        log.debug("User object is saving: $user")
        val savedUser = userRepository.save(user)
        log.debug("Saved user is: $savedUser")
        return savedUser.toUserDTO()
    }

    override fun getUser(username: String): UserDTO? {
        val user = userRepository.findByUsername(username)
        log.debug("User retrieved as: $user")
        return user?.toUserDTO()
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}