package com.gokhana.demo.repository

import com.gokhana.demo.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}