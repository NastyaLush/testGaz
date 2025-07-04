package org.example.hospital.core.appointment

import java.time.LocalDateTime

class AppointmentWithPersonEmail(

    var doctorName: String = "",

    val email: String = "",

    var date: LocalDateTime = LocalDateTime.now(),

    )