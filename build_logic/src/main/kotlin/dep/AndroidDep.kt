package dep

import ext.androidTestImplementation
import ext.implementation
import ext.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object AndroidDep {

    const val `core-kts` = "androidx.core:core-ktx:1.7.0"

    const val `lifecycle-viewmodel-ktx` = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    const val `kotlinx-coroutines-core` = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"

    const val `kotlinx-coroutines-android` = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"

    const val `kotlinx-coroutines-rx2` = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.6.4"

    const val `lifecycle-runtime-ktx` = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"

    const val appcompat = "androidx.appcompat:appcompat:1.4.2"

    const val material = "com.google.android.material:material:1.6.1"

    const val junit = "junit:junit:4.13.2"

    const val `espresso-core` = "androidx.test.espresso:espresso-core:3.4.0"

    const val `androidx-junit` = "androidx.test.ext:junit:1.1.3"
}

fun Project.dependenciesAndroid() = dependencies {
    implementation(AndroidDep.`core-kts`)
    implementation(AndroidDep.appcompat)
    implementation(AndroidDep.material)
    implementation(AndroidDep.`lifecycle-viewmodel-ktx`)
    implementation(AndroidDep.`kotlinx-coroutines-core`)
    implementation(AndroidDep.`kotlinx-coroutines-android`)
    implementation(AndroidDep.`kotlinx-coroutines-rx2`)
    implementation(AndroidDep.`lifecycle-runtime-ktx`)
    testImplementation(AndroidDep.junit)
    androidTestImplementation(AndroidDep.`androidx-junit`)
    androidTestImplementation(AndroidDep.`espresso-core`)
}