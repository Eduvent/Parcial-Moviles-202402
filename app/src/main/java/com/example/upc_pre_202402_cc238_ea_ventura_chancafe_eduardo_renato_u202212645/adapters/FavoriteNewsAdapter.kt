package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.R
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle
import com.squareup.picasso.Picasso

class FavoriteNewsAdapter(
    private var favoriteList: List<NewsArticle>,
    private val listener: OnRemoveClickListener
) : RecyclerView.Adapter<FavoriteNewsAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFavoriteAuthor: TextView = itemView.findViewById(R.id.tvFavoriteAuthor)
        val tvFavoriteTitle: TextView = itemView.findViewById(R.id.tvFavoriteTitle)
        val tvFavoriteDescription: TextView = itemView.findViewById(R.id.tvFavoriteDescription)
        val ivFavoriteImage: ImageView = itemView.findViewById(R.id.ivFavoriteImage)
        val btnRemoveFavorite: ImageButton = itemView.findViewById(R.id.btnRemoveFavorite)

        fun bind(article: NewsArticle) {
            tvFavoriteAuthor.text = article.author ?: "Unknown Author"
            tvFavoriteTitle.text = article.title
            tvFavoriteDescription.text = article.description ?: "No Description"

            if (!article.urlToImage.isNullOrEmpty()) {
                Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(ivFavoriteImage)
            } else {
                ivFavoriteImage.setImageResource(R.drawable.ic_no_image) // Imagen alternativa
            }

            btnRemoveFavorite.setOnClickListener {
                listener.onRemoveClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_favorite_news_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = favoriteList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    fun updateFavorites(newFavorites: List<NewsArticle>) {
        favoriteList = newFavorites
        notifyDataSetChanged()
    }

    interface OnRemoveClickListener {
        fun onRemoveClick(article: NewsArticle)
    }
}