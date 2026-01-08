import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.api.dsl.ApplicationExtension

class AndroidAppPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("com.rms.android-hilt.plugin")
                apply("com.rms.app-compose.plugin")
            }
            extensions.configure(ApplicationExtension::class.java) {
                configureKotlin()
                configureAndroidCommon()
                defaultConfig {
                    applicationId = APP_PACKAGE_ID
                    targetSdk = TARGET_SDK
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
            }
        }
    }
}