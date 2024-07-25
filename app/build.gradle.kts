plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.adobedemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.adobedemo"
        minSdk = 24
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.adobe.marketing.mobile:identity")
    implementation("com.adobe.marketing.mobile:core")

    implementation(platform("com.adobe.marketing.mobile:sdk-bom:2.+"))
    implementation("com.adobe.marketing.mobile:assurance")
    implementation ("com.adobe.marketing.mobile:messaging")
    implementation ("com.adobe.marketing.mobile:edgeidentity")
    implementation ("com.adobe.marketing.mobile:edgeconsent")
    implementation ("com.adobe.marketing.mobile:edge")
    implementation ("com.adobe.marketing.mobile:userprofile")
    implementation ("com.adobe.marketing.mobile:identity")
    implementation ("com.adobe.marketing.mobile:lifecycle")
    implementation ("com.adobe.marketing.mobile:signal")




}