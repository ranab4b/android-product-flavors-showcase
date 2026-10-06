package com.loyaltyhub.app

/**
 * The single seam between shared UI and flavor-specific values.
 *
 * Nothing in [HomeScreen] or anywhere else under `main/` ever checks which
 * flavor is running. It only ever reads these three fields, which are fed
 * by [BuildConfig] constants that each product flavor sets independently.
 */
object BrandConfig {
    val displayName: String = BuildConfig.BRAND_DISPLAY_NAME
    val apiBaseUrl: String = BuildConfig.API_BASE_URL
    val loyaltyEnabled: Boolean = BuildConfig.FEATURE_LOYALTY_ENABLED
}
