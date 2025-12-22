import org.jetbrains.kotlin.gradle.targets.web.nodejs.BaseNodeJsEnvSpec
import org.jetbrains.kotlin.gradle.targets.web.yarn.BaseYarnRootEnvSpec

plugins {
    kotlin("multiplatform") version "2.3.0"
}

group = "dev.ghostflyby"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js {
        nodejs()
        useEsModules()
        generateTypeScriptDefinitions()
        binaries.executable()
    }
}


extensions.configure<BaseNodeJsEnvSpec> {
    download = false
}

extensions.configure<BaseYarnRootEnvSpec>() {
    download = false
}

tasks.configureEach {
    if (name.contains("npm", ignoreCase = true)
        || name.contains("yarn", ignoreCase = true)
        || name.contains("packageJson", ignoreCase = true)
    ) {
        enabled = false
    }
}
