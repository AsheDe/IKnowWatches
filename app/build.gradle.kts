plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.bellalogica.yosderelojes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bellalogica.yosderelojes"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.animation:animation-android:1.5.1")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.navigation:navigation-compose:2.7.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.compose.ui:ui-test-android:1.5.1")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4-android:1.5.1")

    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.1")

    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4-android:1.5.1")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.1")
    testImplementation ("androidx.test:core:1.4.0")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("com.google.truth:truth:1.1.3")
}