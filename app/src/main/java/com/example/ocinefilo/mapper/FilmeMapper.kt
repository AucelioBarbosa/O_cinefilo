package com.example.ocinefilo.mapper

import com.example.ocinefilo.FilmeModel
import com.example.ocinefilo.model.Result

object FilmeMapper {

    private val baseURLImage = "https://image.tmdb.org/t/p/w500"

    fun responseToModel(result: Result): FilmeModel {
        return FilmeModel(
            id = result.id.toString(),
            titulo = result.title,
            sinopse = result.overview,
            imagem = baseURLImage + result.poster_path
        )

    }

    fun responseToModel(list: List<Result>): List<FilmeModel> {
        return list.map { responseToModel(it) }
    }
}