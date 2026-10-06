package com.loyaltyhub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loyaltyhub.app.ui.theme.LoyaltyHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoyaltyHubTheme {
                HomeScreen()
            }
        }
    }
}
