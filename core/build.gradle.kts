plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
}

android {
    namespace = "${BuildInfo.APPLICATION_ID}.core"
    compileSdk = BuildInfo.COMPILE_SDK

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

    implementation("androidx.core:core-ktx:1.13.1")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.material3:material3")
}
