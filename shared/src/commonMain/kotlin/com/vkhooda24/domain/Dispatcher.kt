package com.vkhooda24.domain

import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2019-11-05.
 */

expect val defaultDispatcher: CoroutineContext

expect val uiDispatcher: CoroutineContext