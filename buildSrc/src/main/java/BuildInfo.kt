import org.gradle.api.JavaVersion

object BuildInfo {
    const val APPLICATION_ID = "com.example.demo_android"
    const val MIN_SDK = 30
    const val COMPILE_SDK = 34
    const val TARGET_SDK = 34
    const val VERSION_CODE = 100000
    const val VERSION_NAME = "1.0.0"
    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val JVM_TARGET = "1.8"
    const val COMPOSE_KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.1"
}
