import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.ksp)
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidLibrary {
        namespace = "com.example.sharedmodule"
        compileSdk = 35
        minSdk = 28
    }

    val xcfName = "sharedModuleKit"
    val xcf = XCFramework(xcfName)
    iosX64 {
        binaries.framework {
            baseName = xcfName
            xcf.add(this)
        }
    }
    iosArm64 {
        binaries.framework {
            baseName = xcfName
            xcf.add(this)
        }
    }
    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.room.runtime)                 // Room KMP 런타임
                implementation(libs.androidx.sqlite.bundled) // BundledSQLiteDriver
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.composeVM)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.room.ktx)           // Android 전용 확장
                implementation(libs.coroutines.android)
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
            }
        }

        // iOS 쪽에는 'room-compiler' 넣지 말 것!
        val iosX64Main by getting { }
        val iosArm64Main by getting { }
        val iosSimulatorArm64Main by getting { }
    }
}

// --- KSP: 타깃별로 Room Compiler 연결 ---
dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
}

// (선택) Room 스키마 디렉터리 설정
ksp {
    // schema 출력 경로
    arg("room.schemaLocation", "$projectDir/schemas")
}