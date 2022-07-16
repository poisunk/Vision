package ext

import org.gradle.kotlin.dsl.DependencyHandlerScope

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */

fun DependencyHandlerScope.implementation(dependency: Any) =
    add("implementation", dependency)

fun DependencyHandlerScope.testImplementation(dependency: Any) =
    add("testImplementation", dependency)

fun DependencyHandlerScope.androidTestImplementation(dependency: Any) =
    add("androidTestImplementation", dependency)

fun DependencyHandlerScope.kapt(dependency: Any) =
    add("kapt", dependency)