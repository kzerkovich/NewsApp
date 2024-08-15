package com.kzerk.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kzerk.newsapp.R
import com.kzerk.newsapp.data.entity.Article
import com.kzerk.newsapp.ui.theme.myColor_1
import com.kzerk.newsapp.ui.theme.myColor_2
import com.kzerk.newsapp.ui.theme.myColor_3


@Composable
fun Loader(){
	Column (
		modifier = Modifier
			.fillMaxSize()
			.padding(8.dp)
			.background(myColor_1),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	){
		CircularProgressIndicator(
			modifier = Modifier
				.size(60.dp)
				.padding(10.dp),
			color = myColor_3
		)
	}
}

@Composable
fun NormalTextComponent(textValue: String){
	Text(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight()
			.padding(8.dp),
		text = textValue,
		style = TextStyle(
			fontSize = 18.sp,
			fontWeight = FontWeight.Normal,
			fontFamily = FontFamily.Monospace,
			color = Color.Black
		),
		textAlign = TextAlign.Justify
	)
}


@Composable
fun HeadingTextComponent(textValue: String, centerAligned: Boolean = false){
	Box(
		modifier = Modifier
			.clip(RoundedCornerShape(10.dp))
			.background(myColor_2)
	){
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.padding(8.dp),
			text = textValue,
			style = TextStyle(
				fontSize = 24.sp,
				fontWeight = FontWeight.Medium),
			textAlign = if (centerAligned) TextAlign.Center else TextAlign.Start
		)
	}
	
}

@Composable
fun NewsRowComponent(page: Int, article: Article){
	Column(
		modifier = Modifier
			.background(myColor_1)
			.fillMaxSize()

			.padding(8.dp)
	) {

		AsyncImage(
			modifier = Modifier
				.fillMaxWidth()
				.height(240.dp),
			model = article.urlToImage,
			contentDescription = null,
			contentScale = ContentScale.Fit,
			placeholder = painterResource(id = R.drawable.no_image),
			error = painterResource(id = R.drawable.no_image),
		)

		HeadingTextComponent(textValue = article.title ?: "NA")

		Spacer(modifier = Modifier.size(10.dp))

		NormalTextComponent(textValue = article.description ?: "NA")

		Spacer(modifier = Modifier.weight(1f))

		AuthorDetailsComponent(article.author, article.source?.name, article.url)
	}
}

@Composable
fun AuthorDetailsComponent(authorName: String?, sourceName: String?, url: String?){
	Box(
		modifier = Modifier
			.clip(RoundedCornerShape(10.dp))
			.background(myColor_3)
		)
	{
		Row (
			modifier = Modifier
				.fillMaxWidth()
				.padding(
					start = 10.dp,
					end = 10.dp
				)
		){
			authorName?.also {
				Text(text = "Автор статьи: ")
				Text(text = it, maxLines = 1)
			}
		}

		Row(modifier = Modifier.fillMaxWidth()) {
			Text(
				text = "$sourceName:",
				modifier = Modifier
					.padding(
					start = 10.dp,
					end = 10.dp,
					top = 25.dp,

				)
			)

			val annotatedString = buildAnnotatedString {

				append(url)
				addStyle(
					style = SpanStyle(
						color = Color.Blue,
						textDecoration = TextDecoration.Underline
					),
					start = 0,
					end = url!!.length
				)

				addStringAnnotation(
					tag = "URL",
					annotation = url,
					start = 0,
					end = url.length
				)

			}

			val uriHandler = LocalUriHandler.current

			ClickableText(
				text = annotatedString,
				maxLines = 1,
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						top = 28.dp,
					),
				onClick = {
					annotatedString
						.getStringAnnotations("URL", it, it)
						.firstOrNull()?.let { stringAnnotation ->
							uriHandler.openUri(stringAnnotation.item)
						}
				}
			)
			}
		}
	}


@Composable
fun EmptyStateComponent(){
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(10.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Image(painter = painterResource(
			id = R.drawable.no_news),
			contentDescription = null
		)
		
		HeadingTextComponent(textValue = stringResource(R.string.no_news_text), centerAligned = true)
	}
}