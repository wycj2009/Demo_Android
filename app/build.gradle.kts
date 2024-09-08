plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.app"
    compileSdk = BuildInfo.COMPILE_SDK

    defaultConfig {
        applicationId = BuildInfo.APPLICATION_ID
        minSdk = BuildInfo.MIN_SDK
        targetSdk = BuildInfo.TARGET_SDK
        versionCode = BuildInfo.VERSION_CODE
        versionName = BuildInfo.VERSION_NAME
    }

    compileOptions {
        sourceCompatibility = BuildInfo.JAVA_VERSION
        targetCompatibility = BuildInfo.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = BuildInfo.JVM_TARGET
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildInfo.COMPOSE_KOTLIN_COMPILER_EXTENSION_VERSION
    }
}

dependencies {
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
