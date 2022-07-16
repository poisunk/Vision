package dep

import ext.implementation
import ext.kapt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object ARouter {

    const val `arouter-api` = "com.alibaba:arouter-api:1.5.2"

    const val `arouter-compiler` = "com.alibaba:arouter-compiler:1.5.2"

}

fun Project.dependenciesARouter() = dependencies {
    implementation(ARouter.`arouter-api`)
    kapt(ARouter.`arouter-compiler`)
}