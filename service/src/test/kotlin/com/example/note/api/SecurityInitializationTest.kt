package com.example.note.api

import com.example.note.security.Admin
import com.example.note.security.Member
import com.example.note.security.UserDTO
import com.example.note.service.UserService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.lang.RuntimeException

@RunWith(SpringRunner::class)
@SpringBootTest
class SecurityInitializationTest {

    @Autowired
    private lateinit var userService: UserService

    private val password = "1234"
    private val adminEmail = "admin@ebay.com"
    private val memberEmail = "member@ebay.com"

    @Test
    fun initAdmin(){
        try {
            val admin = userService.loadUserByUsername(adminEmail)
            if (admin is Admin)
                println("Admin exist: ${admin.id}")
            else
                Assert.fail("Is not admin")
        } catch (e: RuntimeException) {
            val toSave = UserDTO(
                    adminEmail,
                    password,
                    "admin",
                    "isiter"
            )
            val saved = userService.saveAdmin(toSave)
            println("Admin instered : ${saved.id}")
        }
    }

    @Test
    fun initMember() {
        try {
            val member = userService.loadUserByUsername(memberEmail)
            if (member is Member)
                println("Member exist: ${member.id}")
            else
                Assert.fail("Is not member")
        } catch (e: RuntimeException){
            val toSave = UserDTO(
                    memberEmail,
                    password,
                    "member",
                    "_1"
            )
            val saved = userService.saveMember(toSave)
            println("Member instered ${saved.id}")
        }
    }

}