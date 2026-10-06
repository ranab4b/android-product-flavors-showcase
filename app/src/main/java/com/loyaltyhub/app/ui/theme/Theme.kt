package com.loyaltyhub.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.loyaltyhub.app.R

@Composable
fun LoyaltyHubTheme(content: @Composable () -> Unit) {
    val brandColor = colorResource(id = R.color.brand_primary)
    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorScheme(primary = brandColor)
    } else {
        lightColorScheme(primary = brandColor)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
