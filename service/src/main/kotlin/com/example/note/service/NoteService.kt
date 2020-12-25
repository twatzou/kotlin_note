package com.example.note.service

import com.example.note.data.db.entity.Note
import com.example.note.data.vo.NoteDTO
import com.example.note.data.db.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("Note service")
class NoteService {

    @Autowired
    lateinit var repository: NoteRepository

    fun getNotes(): Iterable<NoteDTO> = repository.findAll().map {
        it ->
        NoteDTO(it)
    }

    fun insertNote(note: NoteDTO) = NoteDTO(
            repository.save(
                    Note(
                            title = note.title,
                            message = note.message,
                            location = note.location
                    )
            )
    )

    fun deleteNote(id: String) = repository.deleteById(id)

    fun updateNote(note: Note): Note = repository.save(note)

    fun findByTitle(title: String): Iterable<NoteDTO> =
            repository.findByTitle(title).map {
                it ->
                NoteDTO(it)
            }

}