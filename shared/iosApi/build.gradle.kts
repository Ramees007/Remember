import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}

kotlin {

    val xcf = XCFramework("SharedIOSApi")
    val targets = listOf(iosX64(), iosArm64(), iosSimulatorArm64())
    targets.forEach {
        it.binaries.framework {
            baseName = "SharedIOSApi"
            isStatic = true
            binaryOption("bundleId", "com.rms.shared.iosapi")

            export(project(":shared:feature:tasks:presentation"))
            //export(project(":shared:util"))
            export(libs.androidx.lifecycle.viewmodel)
//            export(libs.coroutines)

            xcf.add(this)
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                api(project(":shared:feature:tasks:presentation"))
                implementation("androidx.sqlite:sqlite-bundled:2.5.0")
                // Add KMP dependencies here
                //api(libs.coroutines)
            }
        }

        commonTest {
            dependencies {
                //implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
                //api(libs.androidx.lifecycle.viewmodel)
            }
        }
    }

}

android {
    namespace = "com.rms.shared.iosapi"
}
