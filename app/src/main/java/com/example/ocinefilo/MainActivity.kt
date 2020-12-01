package com.example.ocinefilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var filmesAdapter: FilmesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializandoRecycleView()
        adicionarDataSet()
    }

    private fun adicionarDataSet() {
        val data: ArrayList<FilmeModel> = RepositorioFilme.createDataSet()
        filmesAdapter.submitList(data)
    }

    fun inicializandoRecycleView() {

        recycle_view.apply {

            val toSpacing = SpacingItem(30)
            addItemDecoration(toSpacing)
            layoutManager = LinearLayoutManager(this@MainActivity)
            filmesAdapter = FilmesAdapter()
            adapter = filmesAdapter
        }
    }
}