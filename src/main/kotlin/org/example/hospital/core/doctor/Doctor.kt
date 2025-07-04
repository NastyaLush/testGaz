package org.example.hospital.core.doctor

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Table(name = "doctors")
class Doctor {

    @Id
    var id: UUID = UUID.randomUUID()

    var name: String = ""

    var startedAt: LocalDateTime = LocalDateTime.now()

    var finishedAt: LocalDateTime = LocalDateTime.now()

    var durationSeconds: Long = 0L

    var createdAt: LocalDateTime = LocalDateTime.now()

    var cost: BigDecimal = BigDecimal.ZERO

}