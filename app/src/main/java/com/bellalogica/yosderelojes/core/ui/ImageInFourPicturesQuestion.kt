package com.bellalogica.yosderelojes.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.ui.theme.Black100
import com.bellalogica.yosderelojes.ui.theme.Black20
import com.bellalogica.yosderelojes.ui.theme.Yellow20
import com.bellalogica.yosderelojes.ui.theme.Yellow40
import com.bellalogica.yosderelojes.ui.theme.Yellow80

@Composable
fun ImageInFourPicturesQuestion(modifier: Modifier, image: ImageWrapper, description: String= "", event: () -> Unit) {
        Image(
            painter = rememberAsyncImagePainter((image.resource)),
            contentDescription = description,
            //contentScale = ContentScale.Inside,
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
                .border(4.dp,  Yellow80)
                .clickable { event() }
        )
}