package com.wenance.challenge.util

import com.wenance.challenge.util.Constants.DATETIME_PATTERN
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun <E> List<E>.applyPagination(limit: Int, offset: Int): List<E> {
    return this
        .windowed(size = limit, step = limit, partialWindows = true)
        .elementAtOrElse(offset) { emptyList() }
}

fun ZonedDateTime.toIsoStringDate(): String {
    return this.format(DateTimeFormatter.ofPattern(DATETIME_PATTERN))
}

inline fun <reified R, reified T> R.toDto(): T {
    val klass = T::class.java
    val constructor = klass.getConstructor(R::class.java)
    return constructor.newInstance(this) as T
}
