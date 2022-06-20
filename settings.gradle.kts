pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }

    plugins {
        id("com.android.application") version "7.2.1"
        id("org.jetbrains.kotlin.android") version "1.7.0"
        id("org.jetbrains.kotlin.android.extensions") version "1.7.0"
        id("com.android.library") version "7.2.1"
        id("com.google.firebase.crashlytics") version "2.1.0"
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application", "com.android.library" -> useModule("com.android.tools.build:gradle:7.2.1")
                "com.google.firebase.crashlytics" -> useModule("com.google.firebase:firebase-crashlytics-gradle:2.1.0")
            }
        }
    }
}

include("app")
include(":daraja")

rootProject.name = "Daraja"