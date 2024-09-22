plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.github.alex31n.vividdialog"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.github.alex31n:nobo-button:2.0.7")
    implementation("com.github.alex31n:rich-text:2.0.1")
    implementation("com.github.alex31n:magic-icon:2.0.2")
}

afterEvaluate {
    publishing {
        publications {
            // create a Maven publication called "release"
            create<MavenPublication>("release"){
                from(components["release"])
                groupId = "com.github.alex31n"
                artifactId = "vivid-dialog"
                version = "1.0"
            }
        }
    }
}