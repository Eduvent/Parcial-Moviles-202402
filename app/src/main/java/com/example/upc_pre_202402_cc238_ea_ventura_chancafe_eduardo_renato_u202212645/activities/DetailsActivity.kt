package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.R
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.database.NewsDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private lateinit var tvSourceName: TextView
    private lateinit var ivDetailImage: ImageView
    private lateinit var tvContent: TextView
    private lateinit var btnOpenWeb: Button
    private lateinit var btnAddFavorite: Button

    private lateinit var newsArticle: NewsArticle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        tvSourceName = findViewById(R.id.tvSourceName)
        ivDetailImage = findViewById(R.id.ivDetailImage)
        tvContent = findViewById(R.id.tvContent)
        btnOpenWeb = findViewById(R.id.btnOpenWeb)
        btnAddFavorite = findViewById(R.id.btnAddFavorite)

        newsArticle = intent.getSerializableExtra("article") as NewsArticle

        tvSourceName.text = newsArticle.source.name
        tvContent.text = newsArticle.content ?: "No content available"

        Picasso.get()
            .load(newsArticle.urlToImage)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .into(ivDetailImage)

        btnOpenWeb.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newsArticle.url))
            startActivity(browserIntent)
        }

        btnAddFavorite.setOnClickListener {
            addToFavorites(newsArticle)
        }
    }

    private fun addToFavorites(article: NewsArticle) {
        CoroutineScope(Dispatchers.IO).launch {
            NewsDatabase.getDatabase(applicationContext).newsDao().insert(article)
            runOnUiThread {
                Toast.makeText(this@DetailsActivity, "Agregado a favoritos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}