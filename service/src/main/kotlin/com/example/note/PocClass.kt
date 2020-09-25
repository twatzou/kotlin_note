package com.example.note

import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking<Unit> {

    data class SequenceReq(var table: String, var count: Int)
    data class SequencesReq(var sequences: MutableList<SequenceReq>)
    data class SequenceResp(var table: String, var ids: List<Long>)
    data class SequencesResp(var sequences: List<SequenceResp>)
}

