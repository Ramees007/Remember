import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.api.dsl.ApplicationExtension

class AndroidAppPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure(ApplicationExtension::class.java) {
                configureKotlin()
                configureAndroidCommon()
                defaultConfig {
                    targetSdk = TARGET_SDK
                }
            }

        }


    }
}