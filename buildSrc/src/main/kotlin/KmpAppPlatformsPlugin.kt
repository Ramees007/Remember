import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpAppPlatformsPlugin: KmpCorePlugin() {

    override fun apply(target: Project) {
        super.apply(target)

        with(target){

            plugins.apply("com.android.library")

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget()
                iosArm64()
                iosSimulatorArm64()
                iosX64()
            }

            extensions.configure(LibraryExtension::class.java) {
                configureAndroidCommon()
            }
        }
    }
}