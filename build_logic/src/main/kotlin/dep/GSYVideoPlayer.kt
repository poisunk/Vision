package dep

import ext.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object GSYVideoPlayer {
    const val GSYVideoPlayer = "com.github.CarGuo.GSYVideoPlayer:GSYVideoPlayer:v8.3.1-release-jitpack"
}

fun Project.dependenciesVideoPlayer() = dependencies {
    implementation(GSYVideoPlayer.GSYVideoPlayer)
}