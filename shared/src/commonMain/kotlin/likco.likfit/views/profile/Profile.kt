package likco.likfit.views.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.I18nViewModel
import likco.likfit.viewmodels.ThemeViewModel
import likco.likfit.viewmodels.UserViewModel
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun Profile() = Column {
    val i18n by sharedViewModel<I18nViewModel>()()
    val darkTheme by sharedViewModel<ThemeViewModel>()()
    val user by viewModel(UserViewModel::class) { UserViewModel() }()

    Text(i18n.text)
    Text(if (darkTheme) "Dark" else "Light")
    Text(user?.login ?: "-?-")
}