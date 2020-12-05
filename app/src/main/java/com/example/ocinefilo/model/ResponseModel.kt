package com.example.ocinefilo.model

data class ResponseModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)