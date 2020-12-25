package com.example.note.data.vo

import com.example.note.data.db.entity.Note
import java.util.*

data class NoteDTO(
        var title: String,
        var message: String,
        var location: String = ""
){
    var id: String = ""
    var created: Date = Date()
    var modified: Date = Date()

    //notice this, 当声明了主构造函数，则所有的次构造函数都要直接/间接的
    // 对主构造函数进行调用，使用this关键字
    constructor(note: Note) : this(
            note.title,
            note.message,
            note.location
    ){
        id = note.id
        created = note.created
        modified = note.modified
    }
}