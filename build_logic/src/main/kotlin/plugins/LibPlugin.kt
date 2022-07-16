package plugins

import BasePlugin
import pluginKotlinAndroid
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
open class LibPlugin : BasePlugin() {

    override fun initConfig(project: Project) {
        project.pluginLibrary()
        project.pluginKotlinAndroid()
        project.configAndroidLib()
        super.initConfig(project)
    }
}

fun Project.pluginLibrary() {
    plugins.apply("com.android.library")
}

fun Project.configAndroidLib() = this.extensions.configure(
    "android",
    Action<LibraryExtension> {
        compileSdk = 32

        defaultConfig {
            minSdk = 23
            targetSdk = 32

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        (this as org.gradle.api.plugins.ExtensionAware).extensions.configure(
            "kotlinOptions",
            Action<KotlinJvmOptions> {
                jvmTarget = "1.8"
            }
        )
    }
)