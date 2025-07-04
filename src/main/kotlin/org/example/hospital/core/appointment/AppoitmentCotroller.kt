package org.example.hospital.core.appointment

import org.example.hospital.generated.api.AppointmentManagementApi
import org.example.hospital.generated.api.model.AppointmentCreateDto
import org.example.hospital.generated.api.model.AppointmentResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*

@RestController
class AppointmentController(
    private val appointmentService: AppointmentService
) : AppointmentManagementApi {

    override fun createAppointment(appointmentCreateDto: AppointmentCreateDto): ResponseEntity<AppointmentResponseDto> {
        val dto = appointmentService.createAppointment(appointmentCreateDto)
        return ResponseEntity.created(URI.create("/appointments/${dto.id}")).body(dto)
    }

    override fun deleteAppointment(id: UUID): ResponseEntity<Unit> {
        appointmentService.deleteAppointment(id)
        return ResponseEntity.noContent().build()
    }

    override fun getAppointmentById(id: UUID): ResponseEntity<AppointmentResponseDto> {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id))
    }

    override fun getAppointmentsByDoctorId(doctorId: UUID): ResponseEntity<List<AppointmentResponseDto>> {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId))
    }

    override fun getAppointmentsByPatientId(patientId: UUID): ResponseEntity<List<AppointmentResponseDto>> {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId))
    }

    override fun updateAppointment(
        id: UUID,
        appointmentCreateDto: AppointmentCreateDto
    ): ResponseEntity<AppointmentResponseDto> {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointmentCreateDto))
    }
}