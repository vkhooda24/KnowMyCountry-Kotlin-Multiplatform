package com.vkhooda24.presenter

import com.vkhooda24.BaseViewListener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2020-03-07.
 */
open class CoroutineScopePresenter(
    private val mainContext: CoroutineContext,     // TODO: Use Dispatchers.Main instead when it will be supported on iOS
    private val baseViewListener: BaseViewListener
) : CoroutineScope {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseViewListener.showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler
}