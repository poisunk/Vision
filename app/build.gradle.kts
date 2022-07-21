plugins {
    id("com.plugins.app")
}

dependencies {
    implementation(project(":lib_common"))
    implementation(project(":module_community"))
    implementation(project(":module_home"))
    implementation(project(":module_mine"))
    implementation(project(":module_notification"))
    implementation(project(":module_video"))

}

