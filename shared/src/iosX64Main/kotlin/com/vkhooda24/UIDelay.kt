package com.vkhooda24

import kotlinx.coroutines.*
import platform.Foundation.NSLog
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2019-11-10.
 */
@InternalCoroutinesApi
class UIDelay : CoroutineDispatcher(), Delay {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val queue = dispatch_get_main_queue()
        dispatch_async(queue) {
            block.run()
        }
    }

    @InternalCoroutinesApi
    override fun scheduleResumeAfterDelay(
        timeMillis: Long,
        continuation: CancellableContinuation<Unit>
    ) {
        dispatch_after(
            dispatch_time(DISPATCH_TIME_NOW, timeMillis * 1_000_000),
            dispatch_get_main_queue()
        ) {
            try {
                with(continuation) {
                    resumeUndispatched(Unit)
                }
            } catch (err: Throwable) {
                //BUG: https://youtrack.jetbrains.com/issue/KT-32134
//                NSLog("UNCAUGHT", err.message ?: "", err)
                throw err
            }
        }
    }

    @InternalCoroutinesApi
    override fun invokeOnTimeout(timeMillis: Long, block: Runnable): DisposableHandle {
        val handle = object : DisposableHandle {
            var disposed = false
                private set

            override fun dispose() {
                disposed = true
            }
        }
        dispatch_after(
            dispatch_time(DISPATCH_TIME_NOW, timeMillis * 1_000_000),
            dispatch_get_main_queue()
        ) {
            try {
                if (!handle.disposed) {
                    block.run()
                }
            } catch (err: Throwable) {
//                NSLog("UNCAUGHT", err.message ?: "", err)
                throw err
            }
        }

        return handle
    }
}