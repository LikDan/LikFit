package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import likco.likfit.i18n.Languages
import likco.likfit.services.DataStoreService
import likco.likfit.theme.Themes
import likco.likfit.utils.viewmodels.StateViewModel

class I18nViewModel : StateViewModel<Languages>(
        DataStoreService.load("lang")?.let { t -> Languages.valueOf(t) } ?: Languages.EN_US
) {
    fun change(lang: Languages) {
        DataStoreService.save("lang", lang.name)
        mState.update { lang }
    }

    fun toggle() {
        val lang = when (mState.value) {
            Languages.EN_US -> Languages.CN_CN
            Languages.CN_CN -> Languages.RU_RU
            Languages.RU_RU -> Languages.EN_US
        }
        change(lang)
    }
}