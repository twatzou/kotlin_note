package com.example.note.controller

import com.example.note.data.db.entity.Note
import com.example.note.data.vo.NoteDTO
import com.example.note.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
@EnableAutoConfiguration
class NoteController {

    @Autowired
    private lateinit var noteService: NoteService
    
    @GetMapping("/rozou")
    fun getNote(): String{
        return "rozou"
    }

    @GetMapping(
            value = ["/obtain"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes() = noteService.getNotes()

    @PutMapping(
            value = ["/insert"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(@RequestBody note: NoteDTO) =
            noteService.insertNote(note)

    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteNote(@PathVariable(name = "id") id: String) =
            noteService.deleteNote(id)

    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateNote(@RequestBody note: Note) =
            noteService.updateNote(note)

    @PostMapping(
            value = ["/by_title"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNoteByTitle(@RequestBody payload: NoteFindByTitleRequest):
            Iterable<NoteDTO> = noteService.findByTitle(payload.title)
}

data class NoteFindByTitleRequest(val title: String)