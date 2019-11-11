package com.vkhooda24.domain

import kotlinx.coroutines.*
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

actual val uiDispatcher: CoroutineContext
    get() = IosMainDispatcher

actual val defaultDispatcher: CoroutineContext
    get() = IosMainDispatcher

/**
 * iOS doesn't have a default UI thread dispatcher like [Dispatchers.Main], so we have to implement it. Temp fix, MPP open defect
 * https://github.com/Kotlin/kotlinx.coroutines/issues/470
 */
//private object IosMainDispatcher : CoroutineDispatcher(), Delay {
private object IosMainDispatcher : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) { block.run() }
    }
}

object MainLoopDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock {
            block.run()
        }
    }
}
