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

class NewsAdapter(
    private var newsList: List<NewsArticle>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvYear: TextView = itemView.findViewById(R.id.tvYear)
        val ivNewsImage: ImageView = itemView.findViewById(R.id.ivNewsImage)
        val btnDetails: ImageButton = itemView.findViewById(R.id.btnDetails)

        fun bind(article: NewsArticle) {
            tvAuthor.text = article.author ?: "Unknown Author"
            tvTitle.text = article.title

            // Extraer el año de la fecha de publicación
            val year = article.publishedAt.substring(0, 4)
            tvYear.text = year

            if (!article.urlToImage.isNullOrEmpty()) {
                Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(ivNewsImage)
            } else {
                ivNewsImage.setImageResource(R.drawable.ic_no_image) // Imagen alternativa
            }

            btnDetails.setOnClickListener {
                listener.onDetailsClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    fun updateNews(newNews: List<NewsArticle>) {
        newsList = newNews
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onDetailsClick(article: NewsArticle)
    }
}