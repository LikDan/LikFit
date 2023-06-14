package likco.likfit.views.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import likco.likfit.models.Activity
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun ActivityCard(
    activity: Activity,
    selected: Boolean = false,
    onStart: () -> Unit,
    onEnd: () -> Unit,
    onCurrentInfo: () -> Unit,
    onInfo: () -> Unit,
    modifier: Modifier = Modifier
) =
    Card(
        backgroundColor = if (selected) MaterialTheme.colors.secondary else MaterialTheme.colors.surface,
        modifier = modifier.combinedClickable(
            onLongClick = { if (selected) onCurrentInfo() },
            onClick = {}
        ),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Icon(
                painter = painterResource("${activity.icon}.xml"),
                contentDescription = activity.name,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column {
                Text(activity.name)
                Text(activity.description)
            }

            Spacer(modifier = Modifier.weight(1f))

            if (selected) {
                IconButton(onClick = onEnd) {
                    Icon(Icons.Default.Done, "end")
                }
            } else {
                IconButton(onClick = onStart) {
                    Icon(Icons.Default.Add, "start")
                }
            }
            IconButton(onClick = onInfo) {
                Icon(Icons.Default.Info, "info")
            }
        }
    }