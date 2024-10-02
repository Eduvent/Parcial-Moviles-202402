package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.R
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.adapters.FavoriteNewsAdapter
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.database.NewsDatabase
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteNewsActivity : AppCompatActivity(), FavoriteNewsAdapter.OnRemoveClickListener {

    private lateinit var rvFavoriteNews: RecyclerView
    private lateinit var favoriteNewsAdapter: FavoriteNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_news)

        rvFavoriteNews = findViewById(R.id.rvFavoriteNews)
        rvFavoriteNews.layoutManager = LinearLayoutManager(this)
        favoriteNewsAdapter = FavoriteNewsAdapter(emptyList(), this)
        rvFavoriteNews.adapter = favoriteNewsAdapter

        loadFavoriteNews()
    }

    private fun loadFavoriteNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val favoriteNews: List<NewsArticle> = NewsDatabase.getDatabase(applicationContext).newsDao().getAllFavorites()
            runOnUiThread {
                if (favoriteNews.isNotEmpty()) {
                    favoriteNewsAdapter.updateFavorites(favoriteNews)
                } else {
                    Toast.makeText(this@FavoriteNewsActivity, "No tienes noticias favoritas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onRemoveClick(article: NewsArticle) {
        CoroutineScope(Dispatchers.IO).launch {
            NewsDatabase.getDatabase(applicationContext).newsDao().delete(article)
            runOnUiThread {
                Toast.makeText(this@FavoriteNewsActivity, "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
                loadFavoriteNews()
            }
        }
    }
}