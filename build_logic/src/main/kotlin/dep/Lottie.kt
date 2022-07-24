package dep

import ext.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object Lottie {
  const val lottie = "com.airbnb.android:lottie:5.0.3"
}

fun Project.dependenciesLottie() = dependencies {
  implementation(Lottie.lottie)
}