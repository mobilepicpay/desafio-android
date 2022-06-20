package com.picpay.desafio.android

object Versions {

  object JetBrains {
    object Kotlin {
      const val stdLib = "1.6.10"
      const val gradle = "1.6.10"
    }

    object KotlinX {
      const val coroutines = "1.6.1"
    }
  }

  object Google {
    const val services = "4.3.10"

    object Firebase {
      const val bom = "29.0.4"
      const val crashlyticsGradle = "2.8.1"
    }

    object Gson {
      const val gson = "2.8.7"
    }

    object Truth {
      const val truth = "1.1.3"
    }
  }

  object Android {
    const val gradle = "7.1.3"
    const val material = "1.5.0"
    const val databinding = "3.1.4"
  }

  object AndroidX {
    const val startup = "1.1.1"
    const val appCompat = "1.4.1"
    const val constraintLayout = "2.1.3"
    const val fragment = "1.4.0"
    const val lifecycleCommonJava = "2.3.1"
    const val lifecycleExtensions = "2.3.1"
    const val coreKtx = "1.7.0"
    const val navigation = "2.3.5"

    object Navigation {
      const val fragment = "2.3.5"
      const val ui = "2.3.5"
      const val compose = "2.4.2"
    }

    object Compose {
      const val activity = "1.4.0"
      const val material = "1.1.1"
      const val animation = "1.1.1"
      const val uiTooling = "1.1.1"
      const val viewModelLifecycle = "2.4.1"
      const val runtimeLiveData = "1.1.1"
      const val jUnit = "1.1.1"
    }

    object Lifecycle {
      const val runtime = "2.3.1"
      const val runtimeKtx = "2.4.0"
      const val commonJava8 = "2.3.1"
      const val liveData = "2.3.1"
      const val liveDataKtx = "2.4.0"
      const val viewModel = "2.3.1"
      const val viewModelKtx = "2.4.0"
    }

    object Room {
      const val runtime = "2.4.2"
      const val compiler = "2.4.2"
      const val ktx = "2.4.2"
    }

    object Tests {
      const val runner = "1.4.0"
      const val rules = "1.4.0"
      const val core = "1.4.0"
      const val jUnit = "4.13.2"
      const val extjUnit = "1.1.3"
      const val archCoreTesting = "2.1.0"
    }
  }

  object ThirdParty {
    const val coil = "2.1.0"

    object UiTest  {
      const val roboletric = "4.8"
    }

    object Koin {
      const val koin = "3.1.5"
    }

    object Retrofit {
      const val version = "2.9.0"
      const val moshiConverter = "2.9.0"
      const val gsonConverter = "2.9.0"
      const val scalarsConverter = "2.9.0"
    }

    object Okhttp {
      const val version = "4.9.3"
      const val loggingInterceptor = "4.9.3"
    }

    object Okio {
      const val version = "3.0.0"
    }

    object Karumi {
      const val dexter = "6.2.3"
    }

    object Airbnb {
      const val lottie = "5.0.3"
    }

    object Binaryfork {
      const val spanny = "1.0.4"
    }

    object Tests {
      object Espresso {
        const val rules = "1.4.0"
        const val core = "3.4.0"
      }
    }

    object Bumptech {
      object Glide {
        const val glide = "4.13.0"
        const val compiler = "4.13.0"
      }
    }

    object Mockk {
        const val mockkWebService = "4.10.0"
        const val mockk = "1.12.0"
    }
  }
}
