# Koin!
  Android Studio Version : ArcticFox 2020.3.1 Patch 4
  
  Language : Kotlin

## Project Descrition 
  - Search Coin and Check Coin wallet Application with Bithumb API

## Project File & Contributor (KanuKim97)
  - common
    - Common.kt
    - Constants.kt
  - data/remote
    - model
      - assetsStatus
      - orderBook
      - requestError
      - ticker
      - transaction
    - IKoinApiService.kt
    - RetrofitClient.kt
    - RetrofitRepo.kt
  - viewModel
    - LiveTimeViewModel.kt
    - MainViewModel.kt
    - OrderBookViewModel.kt
    - SelectViewModel.kt
    - TradeViewModel.kt
   - LiveTimeActivity.kt
   - LogoActivity.kt
   - MainActivity.kt
   - OrderBookActivity.kt
   - SelectKoinActivity.kt
   - TradeActivity.kt
  
## APIs
 ### Bithumb API (Public)
  - Ticker
    - Ticker {Path: Coin Name}
    - Ticker {Path: ALL}
  - Transacton
    - Transaction {Path: CoinName}, {Query: Count}
  - Orderbook
    - OrderBook {Path: CoinName}, {Query: Count}
  - AssetsStatus
    - AssetsStatus {Path: CoinName} 
  
 ### Bithumb API (Private)
 
## Sdk Build Version 
  - minSdkVersion : 26
  - targetSdkVersion : 31
  - complieSdkVersion :  31

## Test Enviroment 
 - AVD(Android Virtual Device)
 - Pixel 4 (Android Version 11.0, API 30)

## Dependency 
```
  //navigation View
  implementation "androidx.navigation:navigation-fragment-ktx:2.4.2"
  implementation "androidx.navigation:navigation-ui-ktx:2.4.2"
  
  //Retrofit2
  com.squareup.retrofit2:retrofit:2.3.0
  com.squareup.retrofit2:converter-gson:2.3.0
  
  //Android Jetpack ViewModel
  androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-alpha06
  
  //Android Jetpack LiveData
  androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-alpha06
  
  // Kotlin Coroutine
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
```

## Preference 
```
  Retrofit Kr Document 
  https://devflow.github.io/retrofit-kr/
  
  Android Dev Document - lifecycle
  https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=ko
  
  Bithumb API Docs 
  https://apidocs.bithumb.com/
  
```
