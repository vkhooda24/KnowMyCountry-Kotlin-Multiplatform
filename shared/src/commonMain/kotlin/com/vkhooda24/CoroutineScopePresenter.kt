package com.vkhooda24

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2019-11-10.
 */
open class CoroutineScopePresenter(
    private val mainContext: CoroutineContext,     // TODO: Use Dispatchers.Main instead when it will be supported on iOS, uiCallback: com.vkhooda24.service.uiCallback){}, uiCallback: com.vkhooda24.service.uiCallback){}
    private val baseView: BaseView
) : CoroutineScope {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseView.showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler
//        get() = Dispatchers.Main

    //Cancel coroutine job from Activity onDestroy() method
    open fun onDestroy() {
        job.cancel()
    }
}