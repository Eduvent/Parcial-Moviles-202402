package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.network

import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("articles.php")
    suspend fun getNewsArticles(
        @Query("description") description: String
    ): Response<List<NewsArticle>>
}