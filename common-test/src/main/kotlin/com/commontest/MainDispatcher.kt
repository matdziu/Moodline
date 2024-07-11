package com.commontest

import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@ExtendWith(MainDispatcherExtension::class)
annotation class MainDispatcher