package org.example.hospital.core.appointment

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.util.*

interface AppointmentRepository : CrudRepository<Appointment, UUID> {
    @Query(
        """
            SELECT a.date, p.email, d.name as doctor_name
            FROM appointments a
            JOIN patients p ON a.patient = p.id
            JOIN doctors d ON a.doctor = d.id
            WHERE a.date BETWEEN ? AND ?
            ORDER BY a.date ASC
        """
    )
    fun findByDate(
        now: LocalDateTime
    ): List<AppointmentWithPersonEmail>

    fun findByDoctor(doctorId: UUID): List<Appointment>
    fun findByPatient(patientId: UUID): List<Appointment>

}