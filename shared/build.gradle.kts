plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
}

kotlin {

    androidTarget {
        // Ensure the artifact name is unique if needed
        publishLibraryVariants("release", "debug")
    }

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    iosArm64()
    iosSimulatorArm64()
    iosX64()

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
    namespace = "com.rms.remember.shared"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
