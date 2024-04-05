plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "dev.lantt.wordsfactory"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.lantt.wordsfactory"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val navigationVersion = "2.5.3"
    val koinVersion = "3.3.2"
    val koinComposeVersion = "3.4.1"
    val firebaseBomVersion = "32.7.4"
    val lifecycleComposeVersion = "2.6.0-rc01"
    val retrofitVersion = "2.9.0"
    val loggingInterceptorVersion = "4.11.0"
    val gsonVersion = "2.10.1"
    val gsonConverterVersion = "2.9.0"
    val roomVersion = "2.6.1"
    val lottieVersion = "6.4.0"
    val dataStoreVersion = "1.0.0"

    val navigationCompose = "androidx.navigation:navigation-compose:$navigationVersion"
    val koinCore = "io.insert-koin:koin-core:$koinVersion"
    val koinAndroid = "io.insert-koin:koin-android:$koinVersion"
    val koinAndroidCompose = "io.insert-koin:koin-androidx-compose:$koinComposeVersion"
    val firebaseBom = "com.google.firebase:firebase-bom:$firebaseBomVersion"
    val firebaseAuth = "com.google.firebase:firebase-auth"
    val lifecycleCompose = "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleComposeVersion"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"
    val gson = "com.google.code.gson:gson:$gsonVersion"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:$gsonConverterVersion"
    val room = "androidx.room:room-runtime:$roomVersion"
    val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    val roomKtx = "androidx.room:room-ktx:$roomVersion"
    val lottie = "com.airbnb.android:lottie-compose:$lottieVersion"
    val dataStore = "androidx.datastore:datastore-preferences:$dataStoreVersion"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation(navigationCompose)
    implementation(koinCore)
    implementation(koinAndroid)
    implementation(koinAndroidCompose)
    implementation(platform(firebaseBom))
    implementation(firebaseAuth)
    implementation(lifecycleCompose)
    implementation(retrofit)
    implementation(loggingInterceptor)
    implementation(gson)
    implementation(gsonConverter)
    implementation(room)
    implementation(roomKtx)
    implementation(lottie)
    implementation(dataStore)
    
    annotationProcessor(roomCompiler)
    ksp(roomCompiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}