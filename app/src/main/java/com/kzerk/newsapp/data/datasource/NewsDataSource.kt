package com.kzerk.newsapp.data.datasource

import com.kzerk.newsapp.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

	suspend fun getNewsHeadlines(domains: String) : Response<NewsResponse>

}