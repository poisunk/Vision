import dep.dependenciesGlide
import dep.dependenciesRetrofit
import dep.dependenciesRxJava
import dep.dependenciesVideoPlayer

plugins {
    id("com.plugins.lib")
}

dependenciesGlide()
dependenciesRetrofit()
dependenciesRxJava()
dependenciesVideoPlayer()

dependencies {
    implementation(project(":lib_common"))
}