package com.kzerk.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzerk.newsapp.data.AppConstants
import com.kzerk.newsapp.data.entity.NewsResponse
import com.kzerk.newsapp.ui.repository.NewsRepository
import com.kzerk.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
	private val newsRepository: NewsRepository
) : ViewModel() {

	private val _news : MutableStateFlow<ResourceState<NewsResponse>>
					= MutableStateFlow(ResourceState.Loading())
	val news : StateFlow<ResourceState<NewsResponse>> = _news

	init {
		getNews(AppConstants.DOMAINS)
	}

	private fun getNews(domains : String){
		viewModelScope.launch (Dispatchers.IO){
			newsRepository.getNewsHeadlines(domains)
				.collectLatest { newsResponse ->
					_news.value = newsResponse
				}
		}
	}

}