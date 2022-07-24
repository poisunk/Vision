import dep.*

plugins {
    id("com.plugins.lib")
}

dependenciesRetrofit()
dependenciesRxJava()
dependenciesGlide()
dependenciesVideoPlayer()
dependenciesLottie()

dependencies {
    implementation(project(":lib_common"))
}