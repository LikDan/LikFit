package likco.likfit.views.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import likco.likfit.i18n.Languages
import likco.likfit.i18n.Strings
import likco.likfit.models.Gender
import likco.likfit.services.ui.Navigator
import likco.likfit.services.ui.SnackBarHandler
import likco.likfit.theme.Themes
import likco.likfit.theme.error
import likco.likfit.theme.primary
import likco.likfit.theme.secondary
import likco.likfit.ui.GenderSelection
import likco.likfit.utils.parseUS
import likco.likfit.utils.toUSString
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.I18nViewModel
import likco.likfit.viewmodels.ThemeViewModel
import likco.likfit.viewmodels.UserViewModel

@Composable
fun Profile() = Column(modifier = Modifier.fillMaxSize()) {
    val i18nViewModel = sharedViewModel<I18nViewModel>()
    val i18n by i18nViewModel()

    val themeViewModel = sharedViewModel<ThemeViewModel>()
    val theme by themeViewModel()

    val userViewModel = sharedViewModel<UserViewModel>()
    val user by userViewModel()

    var height by remember { mutableStateOf<Float?>(user!!.profile!!.height) }
    var weight by remember { mutableStateOf<Float?>(user!!.profile!!.weight) }
    var birthday by remember { mutableStateOf(user!!.profile!!.birthday.toUSString()) }
    var gender by remember { mutableStateOf<Gender?>(user!!.profile!!.gender) }
    var weightGoal by remember { mutableStateOf(user!!.profile!!.weightGoal) }
    var stepsGoal by remember { mutableStateOf(user!!.profile!!.stepsGoal) }

    var showGenderSelection by remember { mutableStateOf(false) }
    if (showGenderSelection) {
        GenderSelection(i18n = i18n, back = { showGenderSelection = false }) {
            showGenderSelection = false
            gender = it
        }
    } else {
        Button(onClick = { Navigator("home") }) {
            Icon(Icons.Default.ArrowBack, "back")
        }

        Row {
            Text(user?.login ?: "-?-")
        }

        Column(modifier = Modifier.width(300.dp).align(Alignment.CenterHorizontally)) {
            OutlinedTextField(
                value = height?.toString() ?: "",
                onValueChange = { height = it.toFloatOrNull() ?: height },
                label = { Text(i18n(Strings.HEIGHT)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = weight?.toString() ?: "",
                onValueChange = { weight = it.toFloatOrNull() ?: weight },
                label = { Text(i18n(Strings.WEIGHT)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = birthday,
                onValueChange = { birthday = it },
                label = { Text(i18n(Strings.BIRTHDAY)) },
                placeholder = { Text("mm-dd-yyyy") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = i18n(gender?.toString() ?: ""),
                onValueChange = { },
                label = { Text(i18n(Strings.GENDER)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { if (it.isFocused) showGenderSelection = true },
            )

            Divider(modifier = Modifier.fillMaxWidth())

            OutlinedTextField(
                value = weightGoal?.toString() ?: "",
                onValueChange = {
                    weightGoal = if (it == "") null else it.toFloatOrNull() ?: weightGoal
                },
                label = { Text(i18n(Strings.WEIGHT_GOAL)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = stepsGoal?.toString() ?: "",
                onValueChange = {
                    stepsGoal = if (it == "") null else it.toIntOrNull() ?: stepsGoal
                },
                label = { Text(i18n(Strings.STEPS_GOAL)) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val birthdayLocalDate = LocalDate.parseUS(birthday)
                    userViewModel.updateProfile(
                        height,
                        weight,
                        birthdayLocalDate,
                        gender,
                        weightGoal,
                        stepsGoal
                    ) {
                        SnackBarHandler.success(i18n(Strings.SUCCESS))
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(i18n(Strings.SUBMIT))
            }

            Button(
                onClick = { userViewModel.logout { Navigator("auth/login") } },
                colors = ButtonDefaults.error(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(i18n(Strings.LOGOUT))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            Themes.values().forEach {
                Button(
                    onClick = { themeViewModel.change(it) },
                    colors = if (it == theme) ButtonDefaults.secondary() else ButtonDefaults.primary(),
                    shape = RectangleShape,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(i18n(it.name))
                }
            }
        }

        Row {
            Languages.values().forEach {
                Button(
                    onClick = { i18nViewModel.change(it) },
                    colors = if (it == i18n) ButtonDefaults.secondary() else ButtonDefaults.primary(),
                    shape = RectangleShape,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(it.text)
                }
            }
        }
    }
}