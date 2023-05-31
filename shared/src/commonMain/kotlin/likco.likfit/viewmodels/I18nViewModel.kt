package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import likco.likfit.i18n.Languages
import likco.likfit.utils.viewmodels.StateViewModel

class I18nViewModel : StateViewModel<Languages>(Languages.EN_US) {
    fun change(lang: Languages) = mState.update { lang }

    fun toggle() {
        change(if (mState.value == Languages.EN_US) Languages.RU_RU else Languages.EN_US)
    }
}