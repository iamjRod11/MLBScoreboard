package com.mlb.baseballscores.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import javax.inject.Inject


class DateUtil @Inject constructor() {

    fun formatIsoDateToDisplay(isoDate: String, locale: Locale = Locale.getDefault()): String {
        val utcTime = ZonedDateTime.parse(isoDate) // parses "Z" (UTC) timezone
        val localTime = utcTime.withZoneSameInstant(java.time.ZoneId.systemDefault())

        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withLocale(locale)

        return localTime.format(formatter)
    }
}