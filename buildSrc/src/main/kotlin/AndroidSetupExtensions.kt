import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
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
            kotlinCompilerExtensionVersion = "1.4.0"
        }

        dependencies {
            add("implementation", libs().findLibrary("androidx.compose.runtime").get())
            add("implementation", libs().findLibrary("androidx.lifecycle.vm.compose").get())
            add("implementation", libs().findLibrary("androidx.compose.foundation").get())
            add("implementation", libs().findLibrary("androidx.compose.material3").get())
            add("implementation", libs().findLibrary("androidx.lifecycle.runtime.compose").get())
            add("implementation", libs().findLibrary("androidx.navigation.compose").get())
        }
    }
}

fun Project.libs(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

