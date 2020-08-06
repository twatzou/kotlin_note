package com.example.note.service

import com.example.note.repository.UserRepository
import com.example.note.security.Member
import com.example.note.security.User
import com.example.note.security.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.lang.RuntimeException

class UserService : UserDetailsService {

    @Autowired
    lateinit var repository: UserRepository
    val cryptstrength = 11
    val encoder = BCryptPasswordEncoder(cryptstrength)

    override fun loadUserByUsername(email: String): User? {
        return repository.findUserByEmail(email) ?:
                throw RuntimeException("User not found: $email")
    }

    fun saveMember(user: UserDTO): User{
        val member = Member()
        member.email = user.email
        member.firstName = user.firstName
        member.lastName = user.lastName
        member.pwd = encoder.encode(user.password)
        member.roles = "MEMBER"
        return repository.save(member)
    }


}