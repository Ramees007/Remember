import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltSetupPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("kotlin-kapt")
            }

            dependencies {
                add("implementation", "com.google.dagger:hilt-android:2.44.2")
                add("kapt", "com.google.dagger:hilt-compiler:2.44.2")
            }
        }
    }
}