plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.loyaltyhub.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.loyaltyhub.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
        // BuildConfig generation is off by default on current AGP, so it has
        // to be switched on explicitly for BrandConfig to read anything from it.
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    // One dimension is all a single "which brand is this" axis needs. Each
    // flavor below only sets what's actually different: id suffix, API
    // endpoint, and the loyalty feature flag.
    flavorDimensions += "brand"

    productFlavors {
        create("brewline") {
            dimension = "brand"
            applicationIdSuffix = ".brewline"
            versionNameSuffix = "-brewline"
            buildConfigField("String", "API_BASE_URL", "\"https://api.brewline.example.com\"")
            buildConfigField("Boolean", "FEATURE_LOYALTY_ENABLED", "true")
            buildConfigField("String", "BRAND_DISPLAY_NAME", "\"Brewline\"")
        }
        create("cafenova") {
            dimension = "brand"
            applicationIdSuffix = ".cafenova"
            versionNameSuffix = "-cafenova"
            buildConfigField("String", "API_BASE_URL", "\"https://api.cafenova.example.com\"")
            buildConfigField("Boolean", "FEATURE_LOYALTY_ENABLED", "true")
            buildConfigField("String", "BRAND_DISPLAY_NAME", "\"CafeNova\"")
        }
        create("roasthouse") {
            dimension = "brand"
            applicationIdSuffix = ".roasthouse"
            versionNameSuffix = "-roasthouse"
            buildConfigField("String", "API_BASE_URL", "\"https://api.roasthouse.example.com\"")
            buildConfigField("Boolean", "FEATURE_LOYALTY_ENABLED", "false")
            buildConfigField("String", "BRAND_DISPLAY_NAME", "\"RoastHouse\"")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
}
