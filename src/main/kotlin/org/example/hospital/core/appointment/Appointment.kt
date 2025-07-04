package org.example.hospital.core.appointment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table(name = "appointments")
class Appointment(

    @Id
    val id: UUID = UUID.randomUUID(),

    var doctor: UUID = UUID.randomUUID(),

    val patient: UUID = UUID.randomUUID(),

    var date: LocalDateTime = LocalDateTime.now(),

    )