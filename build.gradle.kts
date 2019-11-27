plugins {
    java
    id("io.freefair.lombok") version "4.1.5"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("commons-io", "commons-io", "2.6")
    implementation("de.siegmar", "fastcsv", "1.0.3")
    implementation("net.dv8tion", "JDA", "4.0.0_64")
    implementation("org.apache.commons",  "commons-lang3", "3.9")
    implementation("org.slf4j", "slf4j-simple", "1.6.1")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.named("assemble") {
    dependsOn(":processResources")
}
tasks.named("build") {
    dependsOn(":processResources")
}