package com.example.ocinefilo

data class FilmeModel (

    val id: String,
    var titulo: String,
    var sinopse: String,
    var imagem: String,
    var favorite: Boolean = false
)