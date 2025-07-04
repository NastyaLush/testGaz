package org.example.hospital.core.doctor

import org.example.hospital.generated.api.model.DoctorCreateDto
import org.example.hospital.generated.api.model.DoctorResponseDto
import java.util.*

interface DoctorService {
    fun getAllDoctors(): List<DoctorResponseDto>
    fun createDoctor(doctorCreateDto: DoctorCreateDto): DoctorResponseDto
    fun deleteDoctor(id: UUID): Unit
    fun getDoctorById(id: UUID): DoctorResponseDto
    fun updateDoctor(id: UUID, doctorCreateDto: DoctorCreateDto): DoctorResponseDto
}