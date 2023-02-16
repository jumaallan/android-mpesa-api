object Versions {

    const val mvnPublishPlugin = "0.24.0"

    // Material and androidX
    const val material = "1.9.0-alpha01"
    const val constraintLayout = "2.1.4"

    // Firebase
    const val firebase = "26.2.0"

    //Networking
    const val retrofit = "2.9.0"
    const val okhttp = "4.10.0"
    const val loggingInterceptor = "4.10.0"

    // Lifecycle
    const val lifecycle = "2.6.0-beta01"

    // Logging - debug builds
    const val timber = "5.0.1"
    const val leakCanary = "2.10"
    const val chucker = "3.5.2"

    // Kotlin
    const val kotlinVersion = "1.8.0"
    const val coreKtx = "1.10.0-alpha02"

    // Gradle Plugins
    const val ktlint = "11.2.0"
    const val detekt = "1.22.0"
    const val spotless = "6.15.0"
    const val jacoco = "0.8.4"
    const val dokka = "1.7.20"
    const val gradleVersionsPlugin = "0.45.0"

    // tests
    const val junit = "4.13"
    const val espresso = "3.5.1"
    const val roboelectric = "4.9.2"
    const val androidXJUnit = "1.1.5"
    const val truth = "1.1.3"
    const val mockWebServer = "4.10.0"
    const val androidXTestCore = "1.5.0"
    const val runner = "1.5.2"
    const val rules = "1.5.0"
    const val archComponentTest = "2.1.0"
    const val kakao = "2.4.0"
    const val mockK = "1.10.0"
    const val liveDataTesting = "1.1.2"
    const val kotlinxCoroutines = "1.6.4"
}

object BuildPlugins {
    //All the build plugins are added here
    const val androidLibrary = "com.android.library"
    const val kapt = "kotlin-kapt"
    const val dagger = "kotlin-android"
    const val ktlintPlugin = "org.jlleitschuh.gradle.ktlint"
    const val detektPlugin = "io.gitlab.arturbosch.detekt"
    const val dokkaPlugin = "org.jetbrains.dokka"
    const val spotlessPlugin = "com.diffplug.spotless"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinAndroidExtensions = "org.jetbrains.kotlin.android.extensions"
    const val gradleVersionsPlugin = "com.github.ben-manes.versions"
    const val jacocoAndroid = "com.hiya.jacoco-android"
    const val mvnPublishPlugin = "com.vanniktech.maven.publish"
}

object Libraries {
    // androidX and Material
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Firebase
    const val bom = "com.google.firebase:firebase-bom:${Versions.firebase}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    // Networking - Retrofit, OKHTTP and loggingInterceptor
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val ohttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    // Lifecycle
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    // Debug - for debug builds only
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val chunkerDebug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chunkerRelease = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

}

object TestLibraries {
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val jUnit = "junit:junit:${Versions.junit}"
    const val androidXJUnit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
    const val roboelectric = "org.robolectric:robolectric:${Versions.roboelectric}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val androidXTestCore = "androidx.test:core:${Versions.androidXTestCore}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val rules = "androidx.test:rules:${Versions.rules}"
    const val archComponentTest = "androidx.arch.core:core-testing:${Versions.archComponentTest}"
    const val kakao = "com.agoda.kakao:kakao:${Versions.kakao}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val androidMockK = "io.mockk:mockk-android:${Versions.mockK}"
    const val liveDataTesting = "com.jraska.livedata:testing-ktx:${Versions.liveDataTesting}"
    const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"
}

object BuildModules {
    const val darajaModule = ":daraja"
}

object AndroidSdk {
    const val minSdkVersion = 16
    const val compileSdkVersion = 33
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.0"
}
