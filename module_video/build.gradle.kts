import dep.dependenciesVideoPlayer

plugins {
    id("com.plugins.lib")
}

dependenciesVideoPlayer()

dependencies {
    implementation(project(":lib_common"))
}