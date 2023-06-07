package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import likco.likfit.i18n.Languages
import likco.likfit.utils.viewmodels.StateViewModel

class ThemeViewModel(initial: Boolean) : StateViewModel<Boolean>(initial) {
    fun change(darkTheme: Boolean) = mState.update { darkTheme }
    fun toggle() = change(!mState.value)
}