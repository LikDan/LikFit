package likco.likfit.utils

import kotlin.time.Duration
import kotlin.time.DurationUnit

fun Duration.Companion.timeDigit(value: Long): String = if (value > 9) value.toString() else "0${value}"

fun Duration.toTimeString(): String  {
    val seconds = this.toLong(DurationUnit.SECONDS)
    val h = Duration.timeDigit(seconds / 3600)
    val m = Duration.timeDigit((seconds % 3600) / 60)
    val s = Duration.timeDigit(seconds % 60)
    return "${h}:${m}:${s}"
}
