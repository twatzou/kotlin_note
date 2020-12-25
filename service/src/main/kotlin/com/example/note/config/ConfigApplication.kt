package com.example.note.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ConfigApplication {

    fun main(args: Array<String>){
        SpringApplication.run(ConfigApplication::class.java, *args)
    }
}