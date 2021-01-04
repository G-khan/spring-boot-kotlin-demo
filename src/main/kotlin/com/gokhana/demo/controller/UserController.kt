package com.gokhana.demo.controller

import com.gokhana.demo.model.UserDTO
import com.gokhana.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun userRegister(@RequestBody @Valid userDTO: UserDTO): ResponseEntity<UserDTO> {
        val savedUser = userService.createUser(userDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    }


    @GetMapping
    fun userRetrieve(@RequestParam(value = "user") @NotEmpty username: String): ResponseEntity<UserDTO> {
        val userDTO = userService.retrieveUser(username)
        return when (userDTO) {
            null -> ResponseEntity.notFound().build()
            else -> {
                ResponseEntity.status(HttpStatus.OK).body(userDTO)
            }
        }
    }

}