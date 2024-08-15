package com.kzerk.newsapp.data.datasource

import com.kzerk.newsapp.data.api.ApiService
import com.kzerk.newsapp.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
	private val apiService : ApiService
) : NewsDataSource {

	override suspend fun getNewsHeadlines(domains: String): Response<NewsResponse> {
		return apiService.getNewsHeadlines(domains)
	}
}