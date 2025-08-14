plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.ksp)
    // alias(libs.plugins.sqlDelight)
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidLibrary {
        namespace = "com.example.sharedmodule"
        compileSdk = 36
        minSdk = 30
    }

    val xcfName = "sharedModuleKit"
    iosX64 { binaries.framework { baseName = xcfName } }
    iosArm64 { binaries.framework { baseName = xcfName } }
    iosSimulatorArm64 { binaries.framework { baseName = xcfName } }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.room.runtime)                 // Room KMP 런타임
                implementation(libs.androidx.sqlite.bundled) // BundledSQLiteDriver
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.composeVM)
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

// (선택) SQLDelight를 같이 쓴다면 아래처럼 유지/수정
/*
sqldelight {
    databases {
        create("MomentSqldelightDatabase") {
            packageName.set("com.example.data.sqldelight")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
        }
    }
}
*/
