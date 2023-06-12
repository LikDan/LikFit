package likco.likfit.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import likco.likfit.i18n.Languages
import likco.likfit.i18n.Strings
import likco.likfit.models.Gender

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenderSelection(i18n: Languages, back: () -> Unit, select: (Gender) -> Unit) = Column {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = back) {
            Icon(Icons.Default.ArrowBack, "back")
        }

        val urlHandler = LocalUriHandler.current
        Button(onClick = { urlHandler.openUri(Gender.URL) }) {
            Icon(Icons.Default.Info, "source")
            Spacer(modifier = Modifier.width(8.dp))
            Text(i18n(Strings.GENDERS_SOURCE))
        }
    }
    Text(i18n(Strings.GENDER_NOT_FOUND))

    LazyColumn {
        items(Gender.values()) {
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { select(it) }
            ) {
                Text(i18n(it.toString()))
            }
        }
    }
}
