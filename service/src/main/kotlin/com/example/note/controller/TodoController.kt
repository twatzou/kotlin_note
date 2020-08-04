package com.example.note.controller

import com.example.note.data.Note
import com.example.note.data.Todo
import com.example.note.data.TodoDTO
import com.example.note.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.awt.PageAttributes
import java.util.*
import javax.xml.ws.Service

data class TodoLaterThanRequest(val date: Date)

@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    private lateinit var service: TodoService

    @GetMapping(
            value = ["/obtain"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodos(): Iterable<Todo> = service.getTodos()

    @PutMapping(
            value = ["/insert"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertTodo(
            @RequestBody todo: Todo
    ): Todo  = service.insertTodo(todo)

    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteTodo(@PathVariable(name = "id") id: String): Unit =
            service.deleteTodo(id)

    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateTodo(@RequestBody todo: Todo): Todo =
            service.updateTodo(todo)

    @PostMapping(
            value = ["/later_than"],
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodosLaterThan(@RequestBody payload: TodoLaterThanRequest):
            Iterable<TodoDTO> = service.getScheduleLaterThan(payload.date)
}