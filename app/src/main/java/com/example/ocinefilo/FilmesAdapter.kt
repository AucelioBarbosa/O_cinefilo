package com.example.ocinefilo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_filme_item.view.*

class FilmesAdapter(private val listener: FilmesAdapterInterface) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface FilmesAdapterInterface {
        fun onFavorite(filmeModel: FilmeModel)
    }

    private var items: List<FilmeModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_filme_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmeViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class FilmeViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val titulo_filme = itemView.tvTitulo
        val sinopse_filme = itemView.tvSinopse
        val imagem_filme = itemView.imgFilme
        val btn_favorite = itemView.icon_favorite

        fun bind(filmeBind: FilmeModel) {
            val context = itemView.context
            val requestOption = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context).applyDefaultRequestOptions(requestOption)
                .load(filmeBind.imagem)
                .into(imagem_filme)
            titulo_filme.setText(filmeBind.titulo)
            sinopse_filme.setText(filmeBind.sinopse)
            btn_favorite.setOnClickListener {
                filmeBind.favorite = !filmeBind.favorite
                listener.onFavorite(filmeBind)
                if(filmeBind.favorite){
                    btn_favorite.setColorFilter(ContextCompat.getColor(context, R.color.red))
                }else{
                    btn_favorite.setColorFilter(ContextCompat.getColor(context, R.color.grey))
                }
            }
            if(filmeBind.favorite){
                btn_favorite.setColorFilter(ContextCompat.getColor(context, R.color.red))
            }else{
                btn_favorite.setColorFilter(ContextCompat.getColor(context, R.color.grey))
            }
        }
    }

    fun submitList(filmeList: List<FilmeModel>) {
        items = filmeList
        notifyDataSetChanged()
    }
}