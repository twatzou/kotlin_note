package com.example.note.data.db.repository

import com.example.note.data.db.entity.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, String>{
    fun findByTitle(title: String): Iterable<Note>
}
