package com.example.koin_apps.data.di.coroutineDispatcher

import javax.inject.Qualifier

/** Coroutine Dispatcher Qualifier **/
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher
