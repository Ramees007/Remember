plugins {
    id("com.rms.kmp-core.plugin")
    id("com.android.library")
}

kotlin {

    androidTarget {
        // Ensure the artifact name is unique if needed
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        androidMain {
            dependencies {
            }
        }
        iosMain {
            dependencies {}
        }
    }
}

android {
    namespace = "com.rms.remember.shared.util"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
