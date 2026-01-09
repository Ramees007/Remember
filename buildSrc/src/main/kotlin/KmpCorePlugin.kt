import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpCorePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            plugins.apply("org.jetbrains.kotlin.multiplatform")

            extensions.configure<KotlinMultiplatformExtension> {
                jvmToolchain { languageVersion.set(JavaLanguageVersion.of(17)) }

                iosArm64()
                iosSimulatorArm64()
                iosX64()
            }
        }
    }
}