package com.gokhana.demo.service

import com.gokhana.demo.entity.User
import com.gokhana.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    override fun getUser(username: String): User? {
        return userRepository.findByUsername(username).orElse(null)
    }
}