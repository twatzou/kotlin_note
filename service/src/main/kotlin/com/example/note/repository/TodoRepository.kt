package com.example.note.repository

import com.example.note.data.Todo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, String> {

    @Query("from Todo t where t.schedule > ?1")
    fun findScheduleLaterThan(date: Long): Iterable<Todo>

}
