package likco.likfit.views.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import likco.likfit.models.UserSteps
import likco.likfit.services.UserStepsService
import likco.likfit.services.ui.Navigator
import likco.likfit.theme.primary
import likco.likfit.theme.secondary
import likco.likfit.ui.charts.BarChart
import likco.likfit.utils.toUSString
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.I18nViewModel
import likco.likfit.viewmodels.StepsCounterViewModel
import likco.likfit.viewmodels.UserViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun StepsInfo() = Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = Modifier.padding(horizontal = 16.dp)
) {
    val i18n by sharedViewModel<I18nViewModel>()()
    val user by sharedViewModel<UserViewModel>()()
    val steps by sharedViewModel<StepsCounterViewModel>()()

    Row(horizontalArrangement = Arrangement.Center) {
        Button(onClick = { Navigator("home") }) {
            Icon(Icons.Default.ArrowBack, "back")
        }
        Text(
            "${i18n("STEPS")} ${steps.total}/${user?.profile?.stepsGoal ?: "-"}",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
    Text(i18n("STEPS_DESCRIPTION"))


    var history by remember { mutableStateOf(emptyList<UserSteps>()) }
    var values by remember { mutableStateOf(emptyList<Pair<String, List<Pair<Float, Color>>>>()) }
    val secondary = MaterialTheme.colors.secondary
    val primary = MaterialTheme.colors.primary
    if (history.isEmpty()) UserStepsService.index {
        fun group(by: (LocalDate) -> Int) = it
            .groupBy { h -> by(h.time) }
            .map { p -> p.value.sumOf { s -> s.steps } }
            .map { s -> s.toFloat() to if (s >= (user?.profile?.stepsGoal ?: 0)) secondary else primary }


        history = it
        values = listOf(
            "MONTH" to group { d: LocalDate -> d.month.ordinal + d.year * 12 },
            "WEEK" to group { d: LocalDate -> d.dayOfWeek.ordinal + d.year * 60 },
            "DAY" to group { d: LocalDate -> d.dayOfYear + d.year * 365 }
        )
    }

    var selectedValue by remember { mutableStateOf(values.lastOrNull()) }
    Row {
        values.forEach {
            Button(
                onClick = { selectedValue = it },
                colors = if (it == selectedValue) ButtonDefaults.secondary() else ButtonDefaults.primary(),
                shape = RectangleShape,
                modifier = Modifier.weight(1f)
            ) {
                Text(i18n(it.first))
            }
        }
    }

    if (selectedValue != null)
        BarChart(
            values = selectedValue!!.second,
            modifier = Modifier.fillMaxWidth(),
        )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(history) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(it.time.toUSString())
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(it.steps.toString())
                            Icon(
                                painter = painterResource("steps.xml"),
                                contentDescription = "steps",
                                modifier = Modifier.size(42.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}