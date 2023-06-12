package likco.likfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import likco.likfit.i18n.Languages
import likco.likfit.i18n.Strings
import likco.likfit.theme.secondary
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.I18nViewModel

@Composable
fun Form(
    title: Strings,
    onSubmit: (() -> Unit)? = null,
    submitButtonText: Strings = Strings.SUBMIT,
    modifier: Modifier = Modifier,
    showLangToggle: Boolean = false,
    showAppName: Boolean = true,
    top: @Composable ColumnScope.(Languages) -> Unit = {},
    bottom: @Composable ColumnScope.(Languages) -> Unit = {},
    content: @Composable ColumnScope.(Languages) -> Unit
) = Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = modifier
        .width(300.dp)
        .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
        .padding(vertical = 8.dp, horizontal = 16.dp)
) {
    val i18nViewModel = sharedViewModel<I18nViewModel>()
    val i18n by i18nViewModel()

    top(i18n)

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text((if (showAppName) "LikFit | " else "") + i18n(title))

        if (showLangToggle)
            Button(
                onClick = { i18nViewModel.toggle() },
                colors = ButtonDefaults.secondary(),
            ) {
                Text(i18n.short)
            }
    }

    Divider(modifier = Modifier.fillMaxWidth())

    content(i18n)

    if (onSubmit != null) {
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(i18n(submitButtonText))
        }
    }

    bottom(i18n)
}