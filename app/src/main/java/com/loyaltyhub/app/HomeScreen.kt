package com.loyaltyhub.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * The one shared screen every flavor ships. It reads [BrandConfig] and the
 * per-flavor color/string resources, and never asks which brand it is.
 */
@Composable
fun HomeScreen() {
    val brandColor = colorResource(id = R.color.brand_primary)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .background(color = brandColor, shape = CircleShape)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                color = brandColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.home_tagline),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(id = R.string.api_endpoint_label, BrandConfig.apiBaseUrl),
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (BrandConfig.loyaltyEnabled) {
                    stringResource(id = R.string.loyalty_enabled_message)
                } else {
                    stringResource(id = R.string.loyalty_disabled_message)
                },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
