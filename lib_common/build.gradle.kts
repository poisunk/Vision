import dep.dependenciesGlide
import dep.dependenciesRetrofit
import dep.dependenciesRxJava
import dep.dependenciesVideoPlayer

plugins {
    id("com.plugins.lib")
}

dependenciesRetrofit()
dependenciesRxJava()
dependenciesGlide()
dependenciesVideoPlayer()