package dep

import ext.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val `converter-gson` = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val `retrofit-rxjava` = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0"
}

fun Project.dependenciesRetrofit() = dependencies {
    implementation(Retrofit.retrofit)
    implementation(Retrofit.`converter-gson`)
    implementation(Retrofit.`retrofit-rxjava`)
}