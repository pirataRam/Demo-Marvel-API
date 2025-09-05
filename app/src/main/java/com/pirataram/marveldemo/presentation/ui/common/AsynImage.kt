package com.pirataram.marveldemo.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AsyncImage(
    model: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    onClick: (url: String) -> Unit = {},
) {
    GlideImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier.clickable { onClick(model) },
        contentScale = contentScale
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun AsyncImagePreview() {
    AsyncImage(
        model = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
        contentDescription = "Marvel Image"
    )
}
