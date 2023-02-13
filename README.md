# Koin!
  Android Studio Version : Electric Eel 2022.1.1
  
  Language : Kotlin ver.1.8.0
 
  Gradle : build Gradle ver.7.4.1
  
  Plugin : kotlin-kapt, dagger-hilt
## Project Descrition 
  - Search cryptocurrency and check crytocurrency price Application with using Bithumb API
  
## Project Contributor
  - [KanuKim97](https://github.com/KanuKim97)
  
## API
 ### Bithumb API (Public)
  - Ticker
    - Ticker {Path: ALL}
    - Ticker {Path: Coin Name}
  - Transacton
    - Transaction {Path: CoinName}, {Query: Count}
  - Orderbook
    - OrderBook {Path: CoinName}, {Query: Count}
 
## Sdk Build Version 
  - minSdkVersion : 26
  - targetSdkVersion : 33
  - complieSdkVersion :  33

## Test Enviroment 
 - AVD(Android Virtual Device)
 - Pixel 6 API 33

## Dependencies
```
//Android X, constraintLayout, navigation
implementation 'androidx.core:core-ktx:1.9.0'
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
implementation "androidx.navigation:navigation-fragment-ktx:2.5.3"
implementation "androidx.navigation:navigation-ui-ktx:2.5.3"

// Android material
implementation 'com.google.android.material:material:1.8.0'

// Android Test Tool
testImplementation 'junit:junit:4.13.2'
androidTestImplementation 'androidx.test.ext:junit:1.1.5'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

// Retrofit2
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"

// Android Jetpack ViewModel, LiveData
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-beta01"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-beta01"

// Kotlin Coroutine
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"

// Room (Local Database)
implementation "androidx.room:room-runtime:2.5.0"
kapt "androidx.room:room-compiler:2.5.0"
implementation "androidx.room:room-ktx:2.5.0"

// Dagger-Hilt DI(Dependency-Injection) tool
implementation "com.google.dagger:hilt-android:2.44.2"
kapt "com.google.dagger:hilt-android-compiler:2.44.2"
```

## Preference 
```
Bithumb API Docs 
https://apidocs.bithumb.com/
  
Android developers - lifecycle
https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=ko
  
Android developers - Room 
https://developer.android.com/training/data-storage/room?hl=ko

Android developers - Coroutines
https://developer.android.com/kotlin/coroutines?hl=ko

Android developers - Dependency Injection
https://developer.android.com/training/dependency-injection?hl=ko
```
