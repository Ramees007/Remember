plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.agp)
    implementation(libs.agp.api)
    implementation(libs.kgp)
    implementation(libs.javapoet)
    gradleApi()
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

        create("JavaLibrarySetupPlugin"){
            id = "com.rms.java-library.plugin"
            implementationClass = "JavaLibrarySetupPlugin"
        }

        create("KmpCorePlugin"){
            id = "com.rms.kmp-core.plugin"
            implementationClass = "KmpCorePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}