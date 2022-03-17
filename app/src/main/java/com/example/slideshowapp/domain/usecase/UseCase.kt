package com.example.slideshowapp.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class UseCase {
    val backgroundContext: CoroutineContext = Dispatchers.IO
    val foregroundContext: CoroutineContext = Dispatchers.Main
    var parentJob: Job = Job()

    abstract fun unsubscribe()

}