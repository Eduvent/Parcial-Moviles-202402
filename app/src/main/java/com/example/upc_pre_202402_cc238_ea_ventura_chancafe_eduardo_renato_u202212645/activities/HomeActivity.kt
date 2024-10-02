package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.R

class HomeActivity : AppCompatActivity() {

    private lateinit var btnFindNews: Button
    private lateinit var btnFavoriteNews: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnFindNews = findViewById(R.id.btnFindNews)
        btnFavoriteNews = findViewById(R.id.btnFavoriteNews)

        btnFindNews.setOnClickListener {
            val intent = Intent(this, FindNewsActivity::class.java)
            startActivity(intent)
        }

        btnFavoriteNews.setOnClickListener {
            val intent = Intent(this, FavoriteNewsActivity::class.java)
            startActivity(intent)
        }
    }
}