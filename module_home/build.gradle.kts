import dep.dependenciesRetrofit
import dep.dependenciesRxJava

plugins {
    id("com.plugins.lib")
}

dependenciesRetrofit()
dependenciesRxJava()

dependencies {
    implementation(project(":lib_common"))
}