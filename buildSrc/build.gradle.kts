plugins {
    `kotlin-dsl`
}


dependencies {
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("com.squareup:javapoet:1.13.0")
}

gradlePlugin {
    plugins {
        create("AndroidAppPlugin") {
            id = "com.rms.android-application.plugin"
            implementationClass = "AndroidAppPlugin"
        }

        create("AndroidModuleSetupPlugin") {
            id = "com.rms.android-library.plugin"
            implementationClass = "AndroidModuleSetupPlugin"
        }

        create("AndroidAppComposePlugin") {
            id = "com.rms.app-compose.plugin"
            implementationClass = "AndroidAppComposePlugin"
        }

        create("AndroidModuleComposePlugin") {
            id = "com.rms.module-compose.plugin"
            implementationClass = "AndroidModuleComposePlugin"
        }

        create("AndroidHiltSetupPlugin") {
            id = "com.rms.android-hilt.plugin"
            implementationClass = "AndroidHiltSetupPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}