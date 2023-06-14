package likco.likfit.ui.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val defaultMaxHeight = 200.dp


@Composable
internal fun BarChart(
    modifier: Modifier = Modifier,
    values: List<Pair<Float, Color>>,
    maxHeight: Dp = defaultMaxHeight
) {
    val borderColor = MaterialTheme.colors.primary
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(maxHeight)
                .drawBehind {
                    // draw X-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )
                    // draw Y-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = strokeWidth
                    )
                }
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        val maxValue = values.maxBy { it.first }.first
        values.forEach { item ->
            Bar(
                value = item.first / maxValue,
                color = item.second,
                maxHeight = maxHeight
            )
        }
    }

}

@Composable
private fun RowScope.Bar(
    value: Float,
    color: Color,
    maxHeight: Dp
) {

    val itemHeight = remember(value) { value * maxHeight.value }

    Spacer(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .height(itemHeight.dp)
            .weight(1f)
            .background(color)
    )

}