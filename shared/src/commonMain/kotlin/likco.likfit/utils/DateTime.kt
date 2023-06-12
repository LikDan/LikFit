package likco.likfit.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val LocalDateTime.Companion.timezone get() = TimeZone.currentSystemDefault()

fun LocalDateTime.Companion.now(): LocalDateTime = Clock.System.now().toLocalDateTime(timezone)
fun LocalDate.Companion.now(): LocalDate = LocalDateTime.now().date
fun LocalTime.Companion.now(): LocalTime = LocalDateTime.now().time

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

fun LocalDate.Companion.parseOrNull(str: String): LocalDate? {
    return runCatching { this.parse(str) }.getOrNull()
}

