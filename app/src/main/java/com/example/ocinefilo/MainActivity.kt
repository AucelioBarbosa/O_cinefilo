package com.example.ocinefilo

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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
        adicionarDataSet()

        check_box.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                filterFavorite()
            }else{
                listFilme()
            }
        }

        fetchMovie()
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

    fun fetchMovie(){
        //chamada para lista de filmes 
        val queue = Volley.newRequestQueue(this)
        val url = "http://www.omdbapi.com/?apikey=a320f108&type=movie&s=rock"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                println(response.toString())
            },
            Response.ErrorListener {
                println("erro!")})
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}