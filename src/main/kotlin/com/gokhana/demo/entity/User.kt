package com.gokhana.demo.entity

import javax.persistence.*

@Entity
data class User(
    @Column(unique=true)
    val username: String,
    val name: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long
)