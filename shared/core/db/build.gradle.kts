plugins {
    id("com.android.library")
    id("com.rms.kmp-core.plugin")
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

kotlin {
    androidTarget()
    sourceSets {
        commonMain {
            dependencies {
                api(libs.room.runtime)
                api(libs.coroutines)
               // api(libs.room.ktx)
            }
        }

        androidMain.dependencies {
            implementation(libs.androidx.room.sqlite.wrapper)
        }
    }
}

android {
    namespace = "com.rms.shared.core.db"
    compileSdk = 36
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    // Add any other platform target you use in your project, for example kspDesktop
}