package org.example.hospital.core.patient

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table(name = "patients")
class Patient(

    @Id
    val id: UUID = UUID.randomUUID(),

    var name: String = "",

    var email: String = "",

    val createdAt: LocalDateTime = LocalDateTime.now()
)