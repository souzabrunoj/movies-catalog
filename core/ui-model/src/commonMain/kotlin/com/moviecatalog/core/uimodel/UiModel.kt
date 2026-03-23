package com.moviecatalog.core.uimodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

public abstract class UiModel<T : UiModelState>(
    initialData: T,
    initialMode: UiMode = UiMode.Content,
) : ViewModel() {
    private val holder = MutableStateFlow(UiState(data = initialData, mode = initialMode))

    public val state: StateFlow<UiState<T>> = holder.asStateFlow()

    protected val currentData: T
        get() = holder.value.data

    protected fun updateData(transform: T.() -> T) {
        holder.update { s ->
            s.copy(data = s.data.transform(), mode = UiMode.Content)
        }
    }

    protected fun setState(
        block: suspend () -> Unit,
        loadingState: () -> UiMode.Loading? = { UiMode.Loading() },
    ) {
        viewModelScope.launch {
            val loading = loadingState()
            if (loading != null) {
                holder.update { s -> s.copy(mode = loading) }
            }
            runCatching {
                block()
            }.onFailure { e ->
                holder.update { s ->
                    s.copy(mode = UiMode.Error(message = e.message.orEmpty(), cause = e))
                }
            }
        }
    }

    protected suspend fun setStateAndAwait(
        loadingState: () -> UiMode.Loading? = { null },
        action: suspend () -> T,
    ) {
        runSetStateTransaction(loadingState, action)
    }

    private suspend fun runSetStateTransaction(
        loadingState: () -> UiMode.Loading?,
        action: suspend () -> T,
    ) {
        val loading = loadingState()
        if (loading != null) {
            holder.update { s -> s.copy(mode = loading) }
        }
        runCatching {
            action()
        }.fold(
            onSuccess = { newData ->
                holder.update { s -> s.copy(data = newData, mode = UiMode.Content) }
            },
            onFailure = { e ->
                holder.update { s ->
                    s.copy(mode = UiMode.Error(message = e.message.orEmpty(), cause = e))
                }
            },
        )
    }
}
