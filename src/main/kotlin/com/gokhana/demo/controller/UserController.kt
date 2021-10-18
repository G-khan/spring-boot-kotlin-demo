package com.gokhana.demo.controller

import com.gokhana.demo.extension.addSignature
import com.gokhana.demo.model.UserDTO
import com.gokhana.demo.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/users")
@Validated
class UserController(
    private val userService: UserService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun userRegister(@RequestBody @Valid userDTO: UserDTO): ResponseEntity<UserDTO> {
        val savedUser = userService.createUser(userDTO)
        log.debug("User Created for $userDTO as $savedUser Signature:${savedUser.name.addSignature()}")
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    }

    @GetMapping
    fun userRetrieve(@RequestParam(value = "username") @NotEmpty username: String): ResponseEntity<UserDTO> {
        val userDTO = userService.getUser(username)
        log.debug("User Retrieved for $username as $userDTO")
        return when (userDTO) {
            null -> ResponseEntity.notFound().build()
            else -> ResponseEntity.status(HttpStatus.OK).body(userDTO)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

}