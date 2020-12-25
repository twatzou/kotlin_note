package com.example.note.data.db.repository

import com.example.note.data.db.entity.Todo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, String> {

    @Query("from Todo t where t.schedule > ?1")
    fun findScheduleLaterThan(date: Long): Iterable<Todo>

}
