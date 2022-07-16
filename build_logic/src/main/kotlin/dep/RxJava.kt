package dep

import ext.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object RxJava {
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:3.1.5"
    const val rxjava = "io.reactivex.rxjava2:rxjava:3.1.5"
}

fun Project.dependenciesRxJava() = dependencies {
    implementation(RxJava.rxjava)
    implementation(RxJava.rxandroid)
}