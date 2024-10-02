package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.database

import androidx.room.*
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: NewsArticle)

    @Query("SELECT * FROM news_articles")
    suspend fun getAllFavorites(): List<NewsArticle>

    @Delete
    suspend fun delete(article: NewsArticle)
}