package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import likco.likfit.services.DataStoreService
import likco.likfit.theme.Themes
import likco.likfit.utils.viewmodels.StateViewModel

class ThemeViewModel(initial: Themes) :
    StateViewModel<Themes>(initial.let {
        DataStoreService.load("theme")?.let { t -> Themes.valueOf(t) } ?: it
    }) {
    fun change(theme: Themes) {
        mState.update {
            theme
        }
        DataStoreService.save("theme", theme.name)
    }

    fun toggle() = change(if (mState.value == Themes.DARK) Themes.LIGHT else Themes.DARK)
}