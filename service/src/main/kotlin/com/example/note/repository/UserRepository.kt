package com.example.note.repository

import com.example.note.security.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {

    fun findUserByEmail(email: String): User?
}