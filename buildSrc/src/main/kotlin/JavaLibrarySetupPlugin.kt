import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

class JavaLibrarySetupPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("kotlin")
            }
            extensions.configure(JavaPluginExtension::class.java) {
                configureKotlin()
            }
        }
    }
}

private fun JavaPluginExtension.configureKotlin() {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}