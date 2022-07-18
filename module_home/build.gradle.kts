import dep.dependenciesGlide
import dep.dependenciesRetrofit
import dep.dependenciesRxJava

plugins {
    id("com.plugins.lib")
}

dependenciesRetrofit()
dependenciesRxJava()
dependenciesGlide()

dependencies {
    implementation(project(":lib_common"))
}