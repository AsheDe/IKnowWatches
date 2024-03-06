package com.bellalogica.yosderelojes.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bellalogica.yosderelojes.ui.theme.MyFontFamily
import com.bellalogica.yosderelojes.ui.theme.Yellow100
import com.bellalogica.yosderelojes.ui.theme.Yellow20
import com.bellalogica.yosderelojes.ui.theme.Yellow80

@Composable
fun ButtonInFourTextsQuestion(modifier: Modifier, text: String, event: () -> Unit) {
    OutlinedButton(
        onClick =  event ,
        border = BorderStroke(2.dp, Yellow80),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        Text(text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Yellow100,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp)
    }

}
