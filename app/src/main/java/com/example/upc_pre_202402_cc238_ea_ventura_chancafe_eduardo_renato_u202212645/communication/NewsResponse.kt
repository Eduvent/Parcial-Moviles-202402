package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.communication

import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)
