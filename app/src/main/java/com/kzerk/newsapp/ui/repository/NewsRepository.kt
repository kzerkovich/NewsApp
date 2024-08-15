package com.kzerk.newsapp.ui.repository

import com.kzerk.newsapp.data.datasource.NewsDataSource
import com.kzerk.newsapp.data.entity.NewsResponse
import com.kzerk.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
	private val newsDataSource: NewsDataSource
) {

	suspend fun getNewsHeadlines(domains : String) : Flow<ResourceState<NewsResponse>> {
		return flow{
			emit(ResourceState.Loading())

			val response = newsDataSource.getNewsHeadlines(domains)

			if (response.isSuccessful && response.body() != null){
				emit(ResourceState.Success(response.body()!!))
			}
			else {
				emit(ResourceState.Error("Ошибка при получении данных"))
			}
		}.catch { e ->
			emit(ResourceState.Error(e.localizedMessage ?: "Ошибка потока"))
		}
	}
}
