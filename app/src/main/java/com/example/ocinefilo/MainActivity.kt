package com.example.ocinefilo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FilmesAdapter.FilmesAdapterInterface {

    private val filmesAdapter = FilmesAdapter(this)
    private var todosFilmes = listOf<FilmeModel>()
    private val sharedPreferences by lazy { getSharedPreferences("FilmesPreferences", Context.MODE_PRIVATE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        inicializandoRecycleView()
        adicionarDataSet()
    }

    private fun adicionarDataSet() {
        val data: ArrayList<FilmeModel> = RepositorioFilme.createDataSet()
        data.forEach { it.favorite = sharedPreferences.contains(it.id) }
        filmesAdapter.submitList(data)
        todosFilmes = data

    }

    fun inicializandoRecycleView() {

        recycle_view.apply {
            val toSpacing = SpacingItem(30)
            addItemDecoration(toSpacing)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = filmesAdapter
        }
    }

    override fun onFavorite(filmeModel: FilmeModel) {
        todosFilmes.find { it.id == filmeModel.id}?.favorite = !filmeModel.favorite
        salvaFilmes(filmeModel.id)
    }

    fun salvaFilmes (id: String){

        sharedPreferences.edit().apply {
            if(sharedPreferences.contains(id)){
                remove(id)
            }else{
                putString(id,"")
            }
            apply()
        }
    }


}