package dep

import ext.implementation
import ext.kapt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object Glide {

    const val glide = "com.github.bumptech.glide:glide:4.13.2"

    const val `glide-compiler` = "com.github.bumptech.glide:compiler:4.13.2"

}

fun Project.dependenciesGlide() = dependencies {
    implementation(Glide.glide)
    kapt(Glide.`glide-compiler`)
}