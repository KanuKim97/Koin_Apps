package com.example.koin_apps.di

import javax.inject.Qualifier

/** Coroutine IoDispatcher Qualifier **/
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

/** Coroutine DefaultDispatcher Qualifier **/
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

/** Coroutine MainDispatcher Qualifier **/
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher
