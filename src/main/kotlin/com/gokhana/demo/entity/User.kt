package com.gokhana.demo.entity

import javax.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    @Column(unique = true)
    val username: String
)