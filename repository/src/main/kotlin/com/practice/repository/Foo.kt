package com.practice.repository

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

interface FooRepository : JpaRepository<Foo, Long>

@Entity
data class Foo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long
)