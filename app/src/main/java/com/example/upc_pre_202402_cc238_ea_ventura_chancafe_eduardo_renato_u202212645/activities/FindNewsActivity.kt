package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.R
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.adapters.NewsAdapter
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.repository.NewsRepository
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.viewmodel.NewsViewModel
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.viewmodel.NewsViewModelFactory

class FindNewsActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var etKeyword: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvNews: RecyclerView
    private lateinit var newsAdapter: NewsAdapter

    private val viewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_news)

        etKeyword = findViewById(R.id.etKeyword)
        btnSearch = findViewById(R.id.btnSearch)
        rvNews = findViewById(R.id.rvNews)

        rvNews.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(emptyList(), this)
        rvNews.adapter = newsAdapter

        btnSearch.setOnClickListener {
            val keyword = etKeyword.text.toString().trim()
            if (keyword.isNotEmpty()) {
                viewModel.fetchNews(keyword)
            } else {
                Toast.makeText(this, "Por favor, ingresa una palabra clave", Toast.LENGTH_SHORT).show()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.newsArticles.observe(this) { articles ->
            newsAdapter.updateNews(articles)
        }

        viewModel.error.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDetailsClick(article: NewsArticle) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}