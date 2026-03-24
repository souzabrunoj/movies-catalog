package com.moviecatalog.core.uimodel

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

private data class CounterState(val count: Int = 0) : UiModelState

private class CounterUiModel : UiModel<CounterState>(CounterState()) {
    fun increment() {
        updateData { copy(count = count + 1) }
    }
}

internal class UiModelUpdateDataTest {

    @Test
    fun updateData_updatesDataAndSetsContentMode() {
        val vm = CounterUiModel()
        vm.increment()
        assertEquals(1, vm.state.value.data.count)
        assertIs<UiMode.Content>(vm.state.value.mode)
    }
}
