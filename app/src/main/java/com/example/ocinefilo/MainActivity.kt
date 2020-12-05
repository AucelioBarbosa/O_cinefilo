package com.example.ocinefilo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ocinefilo.mapper.FilmeMapper
import com.example.ocinefilo.model.ResponseModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FilmesAdapter.FilmesAdapterInterface {

    private val filmesAdapter = FilmesAdapter(this)
    private var todosFilmes = listOf<FilmeModel>()
    private val sharedPreferences by lazy {
        getSharedPreferences(
            "FilmesPreferences",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializandoRecycleView()
        fetchMovie()

        check_box.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                filterFavorite()
            }else{
                listFilme()
            }
        }
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
        salvaFilmes(filmeModel.id)
    }

    fun filterFavorite() {
        filmesAdapter.submitList(todosFilmes.filter { it.favorite })
    }

    fun listFilme() {
        filmesAdapter.submitList(todosFilmes)
    }

    fun salvaFilmes(id: String) {
        sharedPreferences.edit().apply {
            if (sharedPreferences.contains(id)) {
                remove(id)
            } else {
                putString(id, "")
            }
            apply()
        }
    }

    fun fetchMovie() {
        //chamada para lista de filmes 
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://api.themoviedb.org/3/search/movie?api_key=1d7044de1fdd2147a70cd2b3f764ca4c&language=en-US&query=rock&page=1&include_adult=false"
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                val gson = Gson()
                val result = gson.fromJson(response, ResponseModel::class.java)
                val filmes = FilmeMapper.responseToModel(result.results)
                filmes.forEach { it.favorite = sharedPreferences.contains(it.id) }
                filmesAdapter.submitList(filmes)
                todosFilmes = filmes
            },
            Response.ErrorListener {
                println("erro!")
            })
        queue.add(stringRequest)
    }
}