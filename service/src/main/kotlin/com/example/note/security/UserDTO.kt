package com.example.note.security

//for creating new users
data class UserDTO(
        var email: String,
        var password: String,
        var firstName: String,
        var lastName: String
)