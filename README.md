# Koin!
  Android Studio Version : Electric Eel 2022.1.1
  
  Language : Kotlin
  
  Plugin : kotlin-kapt, dagger-hilt
  
## Project Descrition 
  - Search cryptocurrency and check crytocurrency price Application with using Bithumb API
  
## Project Contributor
  - [KanuKim97](https://github.com/KanuKim97)
  
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
  
 ### Bithumb API (Private), Expected
 
## Sdk Build Version 
  - minSdkVersion : 26
  - targetSdkVersion : 33
  - complieSdkVersion :  33

## Test Enviroment 
 - AVD(Android Virtual Device)
 - Pixel 4 (API 33)

## Dependency 
```
  //navigation View
  implementation "androidx.navigation:navigation-fragment-ktx:2.5.3"
  implementation "androidx.navigation:navigation-ui-ktx:2.5.3"
  
  //Retrofit2
  implementation "com.squareup.retrofit2:retrofit:2.9.0"
  implementation "com.squareup.retrofit2:converter-gson:2.9.0"
  
  //Android Jetpack ViewModel
  implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha05"
  
  //Android Jetpack LiveData
  implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha05"
  
  // Kotlin Coroutine
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
  
  //Room
  implementation "androidx.room:room-runtime:2.5.0"
  kapt "androidx.room:room-compiler:2.5.0"
  implementation "androidx.room:room-ktx:2.5.0"
  
  //Dagger
  implementation "com.google.dagger:hilt-android:2.44.2"
  kapt "com.google.dagger:hilt-android-compiler:2.44.2"
```

## Preference 
```
  Retrofit Kr Document 
  https://devflow.github.io/retrofit-kr/
  
  Android developers Document - lifecycle
  https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=ko
  
  Android developers Document - Room 
  https://developer.android.com/training/data-storage/room?hl=ko
  
  Bithumb API Docs 
  https://apidocs.bithumb.com/
```
