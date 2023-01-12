import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun CommonExtension<*, *, *, *>.configureKotlin() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

internal fun CommonExtension<*, *, *, *>.configureAndroidCommon() {
    compileSdk = COMPILE_SDK

    defaultConfig {
        minSdk = MIN_SDK
    }
}

const val MIN_SDK = 21
const val COMPILE_SDK = 33
const val TARGET_SDK = 33

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.3.2"
        }

        dependencies {
            add("implementation", "androidx.compose.runtime:runtime:1.3.2")
            add("implementation", "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha03")
            add("implementation", "androidx.compose.foundation:foundation:1.3.1")
            add("implementation", "androidx.compose.material3:material3:1.0.1")
            add("implementation", "androidx.lifecycle:lifecycle-runtime-compose:2.5.1")
            add("implementation", "androidx.navigation:navigation-compose:2.5.3")
        }
    }
}

