package com.example.note.service

import com.example.note.data.db.entity.Todo
import com.example.note.data.vo.TodoDTO
import com.example.note.data.db.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("Todo Service")
class TodoService {

    @Autowired
    lateinit var repository: TodoRepository

    fun getTodos(): Iterable<Todo> = repository.findAll()

    fun insertTodo(todo: Todo): Todo = repository.save(todo)

    fun deleteTodo(id: String) = repository.deleteById(id)

    fun updateTodo(todo: Todo): Todo = repository.save(todo)

    fun getScheduleLaterThan(date: Date): Iterable<TodoDTO>{
        return repository.findScheduleLaterThan(date.time).map{
            it ->
            TodoDTO(it)
        }
    }



}