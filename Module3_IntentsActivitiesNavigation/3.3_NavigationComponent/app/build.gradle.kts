plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin") // Safe Args plugin
}

android {
    namespace = "com.example.a33navigationcomponent"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.a33navigationcomponent"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.core:core:1.12.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
}