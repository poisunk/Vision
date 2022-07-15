package dep

import base.androidTestImplementation
import base.implementation
import base.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object AndroidDep {

    const val `core-kts` = "androidx.core:core-ktx:1.7.0"

    const val appcompat = "androidx.appcompat:appcompat:1.4.2"

    const val material = "com.google.android.material:material:1.6.1"

    const val junit = "junit:junit:4.13.2"

    const val `espresso-core` = "androidx.test.espresso:espresso-core:1.1.3"

    const val `androidx-junit` = "androidx.test.ext:junit:3.4.0"
}

fun Project.dependenciesAndroid() = dependencies {
    implementation(AndroidDep.`core-kts`)
    implementation(AndroidDep.appcompat)
    implementation(AndroidDep.material)
    testImplementation(AndroidDep.junit)
    androidTestImplementation(AndroidDep.`androidx-junit`)
    androidTestImplementation(AndroidDep.`espresso-core`)
}