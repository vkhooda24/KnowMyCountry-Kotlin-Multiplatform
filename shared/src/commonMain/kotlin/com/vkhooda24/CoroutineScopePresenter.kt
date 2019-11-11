package com.vkhooda24

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2019-11-10.
 */
open class CoroutineScopePresenter(
    private val mainContext: CoroutineContext,     // TODO: Use Dispatchers.Main instead when it will be supported on iOS, uiUpdate: com.vkhooda24.service.UiUpdate){}, uiUpdate: com.vkhooda24.service.UiUpdate){}
    private val baseView: BaseView
) : CoroutineScope {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseView.showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler

    open fun onDestroy() {
        job.cancel()
    }
}