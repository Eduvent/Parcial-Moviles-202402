package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.repository

import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.communication.NewsResponse
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.network.RetrofitInstance
import retrofit2.Response

class NewsRepository {
    suspend fun getNewsArticles(description: String): Response<List<NewsArticle>> {
        return RetrofitInstance.api.getNewsArticles(description)
    }
}