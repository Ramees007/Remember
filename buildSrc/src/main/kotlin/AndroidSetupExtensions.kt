import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun CommonExtension<*, *, *, *, *, *>.configureKotlin() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

internal fun CommonExtension<*, *, *, *, *, *>.configureAndroidCommon() {
    compileSdk = COMPILE_SDK

    defaultConfig {
        minSdk = MIN_SDK
    }
}

const val MIN_SDK = 26
const val COMPILE_SDK = 36
const val TARGET_SDK = 33

const val APP_PACKAGE_ID = "com.rms.remember"

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {

    commonExtension.apply {

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.4"
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

