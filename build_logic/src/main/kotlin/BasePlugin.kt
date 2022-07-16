import dep.dependenciesARouter
import dep.dependenciesAndroid
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
open class BasePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        initConfig(target)
    }

    open fun initConfig(project: Project){
        project.configKapt()
        project.dependenciesAndroid()
        project.dependenciesARouter()
    }
}

fun Project.configKapt() {
    extensions.configure(
        "kapt",
        Action<KaptExtension> {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
    )
}

fun Project.pluginKotlinAndroid() {
    plugins.apply("org.jetbrains.kotlin.android")
    plugins.apply("kotlin-kapt")
}