package com.kzerk.newsapp.data.api

import com.kzerk.newsapp.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("v2/everything")
	suspend fun getNewsHeadlines(
		@Query("domains") domains : String,
		@Query("apiKey") apiKey : String = "fadc77920bb04059a994d810a1755cf8"
	) : Response<NewsResponse>

}