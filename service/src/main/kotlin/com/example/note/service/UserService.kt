package com.example.note.service

import com.example.note.data.db.repository.UserRepository
import com.example.note.security.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import java.lang.RuntimeException

@Repository
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

    fun saveAdmin(user: UserDTO): User{
        val admin = Admin()
        admin.email = user.email
        admin.firstName = user.firstName
        admin.lastName = user.lastName
        admin.pwd = encoder.encode(user.password)
        admin.roles = "ADMIN, MEMBER"
        return repository.save(admin)
    }

    fun deleteUser(id: String) = repository.deleteById(id)

    fun updateUser(toSave: User) : User?{
        val user = repository.findUserByEmail(toSave.email)
                ?: throw RuntimeException("user not found")
        user?.let {
            user.firstName = toSave.firstName
            user.lastName = toSave.lastName
            user.accountNonExpired = toSave.accountNonExpired
            user.accountNonLocked = toSave.accountNonLocked
            user.credentialsNonExpired = toSave.credentialsNonExpired
            user.modified = toSave.modified
            return repository.save(user)
        }
    }

    fun getUsers() = repository.findAll().map { it ->
        UserDetailsDTO(
                it.id,
                it.email,
                it.firstName,
                it.lastName,
                it.roles,
                it.enabled,
                it.accountNonExpired,
                it.accountNonLocked,
                it.credentialsNonExpired,
                it.created,
                it.modified
        )
    }

}