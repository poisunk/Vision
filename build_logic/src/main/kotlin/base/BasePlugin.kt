package base

import dep.dependenciesAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
open class BasePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        initConfig(target)
    }

    open fun initConfig(project: Project){
        project.dependenciesAndroid()
    }
}

fun Project.pluginKotlinAndroid() {
    plugins.apply("org.jetbrains.kotlin.android")
}