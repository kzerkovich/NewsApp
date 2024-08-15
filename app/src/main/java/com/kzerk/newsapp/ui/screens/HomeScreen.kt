package com.kzerk.newsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue // !!!
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kzerk.newsapp.ui.components.EmptyStateComponent
import com.kzerk.newsapp.ui.components.Loader
import com.kzerk.newsapp.ui.components.NewsRowComponent
import com.kzerk.newsapp.ui.theme.myColor_1
import com.kzerk.newsapp.ui.viewmodel.NewsViewModel
import com.kzerk.utilities.ResourceState

const val TAG = "HomeScreen"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()){

	val newsRes by newsViewModel.news.collectAsState()

	val pagerState = rememberPagerState (
		initialPage = 0,
		initialPageOffsetFraction = 0f
	) {
		100
	}

	VerticalPager (
		state = pagerState,
		modifier = Modifier
			.fillMaxSize()
			.background(myColor_1),
		pageSize = PageSize.Fill,
		pageSpacing = 10.dp
	) { page : Int ->
		when(newsRes){
			is ResourceState.Loading -> {
				Log.d(TAG, "Inside_Loading")
				Loader()
			}

			is ResourceState.Success -> {
				val response = (newsRes as ResourceState.Success).data
				Log.d(TAG, "Inside_Success ${response.status} = ${response.totalResults}")


				if(response.articles.isNotEmpty()) {
					NewsRowComponent(page, response.articles[page])
				}
				else {
					EmptyStateComponent()
				}
			}

			is ResourceState.Error -> {
				val error = (newsRes as ResourceState.Error)

				Log.d(TAG, "Inside_Error $error")
			}
		}

	}


}