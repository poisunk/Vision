plugins {
    `kotlin-dsl`
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
}

gradlePlugin {
    plugins {
        create("base-plugin") {
            // 在 app 模块需要通过 id 引用这个插件
            id = "com.base.plugin"
            // 实现这个插件的类的路径
            implementationClass = "base.BasePlugin"
        }

        create("app-plugin") {
            id = "com.plugins.app"
            implementationClass = "plugins.AppPlugin"
        }

        create("lib-plugin") {
            id = "com.plugins.lib"
            implementationClass = "plugins.LibPlugin"
        }

        create("module-plugin") {
            id = "com.plugins.module"
            implementationClass = "plugins.ModulePlugin"
        }
    }
}