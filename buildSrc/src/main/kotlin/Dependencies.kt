object Versions {

    // Material and androidX
    const val material = "1.3.0-alpha02"
    const val constraintLayout = "2.0.1"

    // Firebase
    const val firebase = "26.2.0"

    //Networking
    const val retrofit = "2.9.0"
    const val okhttp = "4.8.1"
    const val loggingInterceptor = "4.8.1"

    // Lifecycle
    const val lifecycle = "2.2.0"

    // DI - Dagger
    const val dagger = "2.14.1"
    const val pengrad = "1.0"

    // Logging - debug builds
    const val timber = "4.7.1"
    const val leakCanary = "2.4"
    const val chucker = "3.4.0"

    // Kotlin
    const val kotlinVersion = "1.4.0"
    const val coreKtx = "1.5.0-alpha02"

    // Gradle Plugins
    const val ktlint = "9.3.0"
    const val detekt = "1.12.0"
    const val spotless = "5.3.0"
    const val jacoco = "0.8.4"
    const val dokka = "1.4.0-rc-24"
    const val gradleVersionsPlugin = "0.29.0"

    // tests
    const val junit = "4.13"
    const val espresso = "3.3.0"
    const val roboelectric = "4.4-beta-1"
    const val androidXJUnit = "1.1.1"
    const val truth = "1.0.1"
    const val mockWebServer = "4.8.1"
    const val androidXTestCore = "1.3.0"
    const val runner = "1.3.0"
    const val rules = "1.3.0"
    const val archComponentTest = "2.1.0"
    const val kakao = "2.3.4"
    const val mockK = "1.10.0"
    const val liveDataTesting = "1.1.2"
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
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    // DI - Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val pengrad = "com.github.pengrad:jdk9-deps:${Versions.pengrad}"

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
    const val archComponentTest =
            "androidx.arch.core:core-testing:${Versions.archComponentTest}"
    const val kakao = "com.agoda.kakao:kakao:${Versions.kakao}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val androidMockK = "io.mockk:mockk-android:${Versions.mockK}"
    const val liveDataTesting = "com.jraska.livedata:testing-ktx:${Versions.liveDataTesting}"
}

object BuildModules {
    const val darajaModule = ":daraja"
}

object AndroidSdk {
    const val minSdkVersion = 16
    const val compileSdkVersion = 30
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.0"
}
