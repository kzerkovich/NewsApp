package com.kzerk.newsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kzerk.newsapp.ui.navigation.AppNavigationGraph
import com.kzerk.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kzerk.newsapp.ui.theme.myColor_1

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		installSplashScreen()
		setContent {
			NewsAppTheme {
				Surface(
					modifier = Modifier
						.fillMaxSize()
						.background(myColor_1)
				) {
					NewsAppEntryPoint()
				}

			}
		}
	}
}

@Composable
fun NewsAppEntryPoint(){
	AppNavigationGraph()
}
