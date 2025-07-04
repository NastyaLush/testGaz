package org.example.hospital.core

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Component
class LocalToOffsetDateTimeConverter :
    Converter<LocalDateTime?, OffsetDateTime?> {
    private val clock: Clock? = null

    override fun convert(source: LocalDateTime): OffsetDateTime? {
        return source.atZone(clock!!.zone).toOffsetDateTime()
    }
}