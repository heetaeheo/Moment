plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
//    id("org.jetbrains.kotlin.native.cocoapods")
    alias(libs.plugins.sqlDelight)
}

kotlin {
    sqldelight {
        databases {
            create("MomentDatabase") {
                packageName.set("com.moment.database")
            }
        }
    }

//    cocoapods {
//        version = "1.0.0" // ✅ 이 줄 추가
//        summary = "Shared module for KMM app"
//        homepage = "https://example.com"
//        ios.deploymentTarget = "14.1"
//        podfile = project.file("../iosApp/Podfile")
//        framework {
//            baseName = "shared"
//        }
//    }

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.example.sharedmodule"
        compileSdk = 36
        minSdk = 30


//        withDeviceTestBuilder {
//            sourceSetTreeName = "test"
//        }.configure {
//            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "sharedModuleKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.sqldelight.coroutines)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.android)
            }
        }

        // 👇 명시적으로 생성하고 그 하위가 의존하도록 설정해야 함
        val iosMain by creating {
            dependencies {
                implementation(libs.sqldelight.native)
            }
        }
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}