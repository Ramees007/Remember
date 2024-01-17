plugins {
    id("com.rms.java-library.plugin")
}

dependencies {
    implementation(project(":core:util"))
    api(project(":tasks:data"))
    testImplementation(libs.junit)
}