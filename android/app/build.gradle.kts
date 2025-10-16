// Usa os plugins essenciais para uma aplicação Android e Kotlin.
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.flutter.flutter-gradle-plugin") // Plugin do Flutter
}

// ✅ PARTE 1 DA CORREÇÃO:
// Define variáveis para receber o versionCode e versionName do Flutter (via pubspec.yaml).
val flutterVersionCode: String? by project
val flutterVersionName: String? by project

android {
    // Define o namespace do seu aplicativo, importante para o Android.
    namespace = "com.example.curly_journey"
    compileSdk = 34 // Versão do SDK do Android para compilar.

    // Configuração para o código fonte.
    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
        }
    }

    defaultConfig {
        // Configurações padrão do aplicativo.
        applicationId = "com.example.curly_journey"
        minSdk = 21      // Mínima versão do Android que seu app suporta.
        targetSdk = 34     // Versão do Android para a qual o app foi otimizado.

        // ✅ PARTE 2 DA CORREÇÃO:
        // Pega os valores das variáveis definidas no topo e os atribui.
        // Usa valores padrão ("1" e "1.0") caso não consiga encontrar os do Flutter.
        versionCode = (flutterVersionCode ?: "1").toInt()
        versionName = flutterVersionName ?: "1.0"
    }

    buildTypes {
        getByName("release") {
            // Configurações para a build de lançamento (o APK que você gera).
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Assina o app para garantir sua autenticidade.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    // Define a compatibilidade com versões do Java.
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // Configurações específicas do Kotlin.
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Dependências do projeto.
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.23")
}
