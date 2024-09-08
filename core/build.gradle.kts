plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.core"
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
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("androidx.compose.material3:material3")
}
