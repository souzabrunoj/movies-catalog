package com.moviecatalog.core.tests

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
public fun runViewModelTest(testBody: suspend TestScope.() -> Unit): TestResult = runTest {
    val mainDispatcher = StandardTestDispatcher(testScheduler)
    Dispatchers.setMain(mainDispatcher)
    try {
        testBody()
    } finally {
        Dispatchers.resetMain()
    }
}
