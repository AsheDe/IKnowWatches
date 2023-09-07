package com.bellalogica.yosderelojes.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bellalogica.yosderelojes.game.model.ImageWrapper

@Composable
fun ImageInFourPicturesQuestion(modifier: Modifier, image: ImageWrapper, description: String= "", event: () -> Unit) {
        Image(
            painter = rememberAsyncImagePainter((image.resource)),
            contentDescription = description,
            modifier = modifier
                .padding(8.dp)
                .border(2.dp,  MaterialTheme.colorScheme.primary)
                .clickable { event() }
        )
}