plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
}

android {
    namespace = "${BuildInfo.APPLICATION_ID}.paging"
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
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.paging:paging-runtime-ktx:3.3.6")
    implementation("androidx.paging:paging-compose:3.3.6")
}
