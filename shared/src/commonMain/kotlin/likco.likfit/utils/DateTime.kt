package likco.likfit.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

val LocalDateTime.Companion.timezone get() = TimeZone.currentSystemDefault()

fun LocalDateTime.Companion.now(): LocalDateTime = Clock.System.now().toLocalDateTime(timezone)
fun LocalDate.Companion.now(): LocalDate = LocalDateTime.now().date
fun LocalTime.Companion.now(): LocalTime = LocalDateTime.now().time

fun LocalDateTime.minus(other: LocalDateTime): Duration {
    return toInstant(LocalDateTime.timezone).minus(other.toInstant(LocalDateTime.timezone))
}

fun LocalDateTime.toUSString(): String {
    return "${this.date.toUSString()} ${this.time.toUSString()}"
}

fun LocalDate.Companion.parseUS(str: String): LocalDate? {
    val iso = str
        .split("-")
        .let { "${it.getOrNull(2)}-${it.getOrNull(0)}-${it.getOrNull(1)}" }
    return runCatching { this.parse(iso) }.getOrNull()
}

fun LocalDate.toUSString(): String {
    return this.toString()
        .split("-")
        .let { "${it.getOrNull(1)}-${it.getOrNull(2)}-${it.getOrNull(0)}" }
}

fun LocalTime.Companion.parseDigit(digit: Int) = if (digit > 9) digit.toString() else "0${digit}"


fun LocalTime.toUSString(): String {
    val h = LocalTime.parseDigit(this.hour)
    val m = LocalTime.parseDigit(this.minute)
    val s = LocalTime.parseDigit(this.second)
    return "${h}:${m}:${s}"
}

fun LocalDate.Companion.parseOrNull(str: String): LocalDate? {
    return runCatching { this.parse(str) }.getOrNull()
}

fun LocalDateTime.Companion.parseOrNull(str: String): LocalDateTime? {
    return runCatching { this.parse(str) }.getOrNull()
}

