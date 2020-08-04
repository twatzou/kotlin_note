package com.example.note.repository

import com.example.note.data.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, String>{
    fun findByTitle(title: String): Iterable<Note>
}
