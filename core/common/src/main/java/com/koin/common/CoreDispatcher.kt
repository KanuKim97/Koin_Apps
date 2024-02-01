package com.koin.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CoreDispatcher(val dispatcher: Dispatcher)

enum class Dispatcher { IO, DEFAULT }