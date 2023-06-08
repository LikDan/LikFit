package likco.likfit.utils.viewmodels

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.viewmodel.ViewModel

open class StateViewModel<T>(initial: T) : ViewModel() {
    val value: T get() = mState.value

    protected val mState = MutableStateFlow(initial)
    val state: StateFlow<T> = mState.asStateFlow()

    @Composable
    fun observable() = mState.collectAsStateWithLifecycle(state.value)

    @Composable
    operator fun invoke() = observable()
}