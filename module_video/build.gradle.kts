import dep.*

plugins {
    id("com.plugins.lib")
}

dependenciesGlide()
dependenciesRetrofit()
dependenciesRxJava()
dependenciesVideoPlayer()
dependenciesLottie()

dependencies {
    implementation(project(":lib_common"))
}