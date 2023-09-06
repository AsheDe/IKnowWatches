package com.bellalogica.yosderelojes.start.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bellalogica.yosderelojes.R
import kotlinx.coroutines.delay

@Composable
fun StartScreen(
    startScreenState: StartScreenInfo,
    onEvent: (StartScreenEvents) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp, top = 64.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        var enabled by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = true) {
            delay(500L)
            enabled = true
        }

        val alpha: Float by animateFloatAsState(if (enabled) 1f else 0.3f)

        Text(
            text = stringResource(id = R.string.start_screen_how_much_u_know),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .weight(1f)
                .graphicsLayer(alpha = alpha)
        )

        OutlinedButton(
            onClick = { onEvent(StartScreenEvents.EnterGameClicked) },
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 24.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (startScreenState) {
                    StartScreenInfo.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.scrim,
                            trackColor = MaterialTheme.colorScheme.error
                        )
                    }

                    StartScreenInfo.Error -> {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = StartScreenInfo.Error::class.simpleName.toString(),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }

                    StartScreenInfo.Success -> {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = StartScreenInfo.Error::class.simpleName.toString(),
                            tint = MaterialTheme.colorScheme.scrim
                        )
                    }
                }
                Spacer(Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.start_screen_show_us),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreviewSuccess() {
    StartScreen(startScreenState = StartScreenInfo.Success, onEvent = {})
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreviewLoading() {
    StartScreen(startScreenState = StartScreenInfo.Loading, onEvent = {})
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreviewError() {
    StartScreen(startScreenState = StartScreenInfo.Error, onEvent = {})
}