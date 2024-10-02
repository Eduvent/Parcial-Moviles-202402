package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.viewmodel

import androidx.lifecycle.*
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.NewsArticle
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsArticles = MutableLiveData<List<NewsArticle>>()
    val newsArticles: LiveData<List<NewsArticle>> = _newsArticles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchNews(description: String) {
        viewModelScope.launch {
            try {
                val response = repository.getNewsArticles(description)
                if (response.isSuccessful) {
                    _newsArticles.value = response.body()
                } else {
                    _error.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Fallo de red: ${e.message}"
            }
        }
    }
}

class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}