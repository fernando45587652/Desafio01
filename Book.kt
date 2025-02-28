package com.example.desafio01

data class Book(
    val id: String = "", // Firebase necesita un constructor vacío
    val titulo: String = "",
    val autor: String = "",
    val signosis: String = "",
)

