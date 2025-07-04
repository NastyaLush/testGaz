package org.example.hospital.core.appointment

import org.example.hospital.generated.api.model.AppointmentCreateDto
import org.example.hospital.generated.api.model.AppointmentResponseDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AppointmentService(
    private val appointmentRepository: AppointmentRepository,
    private val appointmentMapper: AppointmentMapper
) {

    @Transactional
    fun createAppointment(dto: AppointmentCreateDto): AppointmentResponseDto {
        val appointment = appointmentMapper.toEntity(dto)
        return appointmentMapper.toDto(appointmentRepository.save(appointment))
    }

    @Transactional
    fun updateAppointment(id: UUID, dto: AppointmentCreateDto): AppointmentResponseDto {
        val existing = appointmentRepository.findById(id)
            .orElseThrow { RuntimeException("Appointment not found") }

        appointmentMapper.updateEntityFromDto(dto, existing)
        return appointmentMapper.toDto(appointmentRepository.save(existing))
    }

    fun getAppointmentById(id: UUID): AppointmentResponseDto {
        val appointment = appointmentRepository.findById(id)
            .orElseThrow { RuntimeException("Appointment not found") }
        return appointmentMapper.toDto(appointment)
    }

    fun getAppointmentsByDoctorId(doctorId: UUID): List<AppointmentResponseDto> {
        return appointmentRepository.findByDoctor(doctorId).map(appointmentMapper::toDto)
    }

    fun getAppointmentsByPatientId(patientId: UUID): List<AppointmentResponseDto> {
        return appointmentRepository.findByPatient(patientId).map(appointmentMapper::toDto)
    }

    @Transactional
    fun deleteAppointment(id: UUID) {
        if (!appointmentRepository.existsById(id)) {
            throw RuntimeException("Appointment not found")
        }
        appointmentRepository.deleteById(id)
    }
}