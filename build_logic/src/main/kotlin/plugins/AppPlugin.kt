package plugins

import BasePlugin
import pluginKotlinAndroid
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class AppPlugin : BasePlugin() {

    override fun initConfig(project: Project) {

        project.pluginApplication()
        project.pluginKotlinAndroid()
        project.configAndroidApp()
        super.initConfig(project)
    }
}

fun Project.pluginApplication() {
    plugins.apply("com.android.application")
}

fun Project.configAndroidApp() = this.extensions.configure(
    "android",
    Action<BaseAppModuleExtension> {
        compileSdk = 32

        defaultConfig {
            applicationId = "com.example.vision"
            minSdk = 23
            targetSdk = 32
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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