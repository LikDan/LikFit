package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import likco.likfit.theme.Themes
import likco.likfit.utils.viewmodels.StateViewModel

class ThemeViewModel(initial: Themes) : StateViewModel<Themes>(initial) {
    fun change(darkTheme: Themes) = mState.update { darkTheme }
    fun toggle() = change(if (mState.value == Themes.DARK) Themes.LIGHT else Themes.DARK)
}