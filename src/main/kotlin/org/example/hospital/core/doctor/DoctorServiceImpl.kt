package org.example.hospital.core.doctor

import org.example.hospital.generated.api.model.DoctorCreateDto
import org.example.hospital.generated.api.model.DoctorResponseDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DoctorServiceImpl(
    private val doctorMapper: DoctorMapper,
    private val doctorRepository: DoctorRepository
) : DoctorService {
    @Transactional(readOnly = true)
    override fun getAllDoctors(): List<DoctorResponseDto> {
        return doctorMapper.toResponseDtoList(doctorRepository.findAll())
    }

    @Transactional
    override fun createDoctor(doctorCreateDto: DoctorCreateDto): DoctorResponseDto {
        val doctor = doctorMapper.toEntity(doctorCreateDto)
        val saved = doctorRepository.save(doctor)
        return doctorMapper.toResponseDto(saved)
    }

    @Transactional
    override fun deleteDoctor(id: UUID): Unit {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id)
        }
    }

    @Transactional(readOnly = true)
    override fun getDoctorById(id: UUID): DoctorResponseDto {
        val doctor = doctorRepository.findById(id)
            .orElseThrow { NoSuchElementException("Doctor with ID $id not found") }
        return doctorMapper.toResponseDto(doctor)
    }

    @Transactional
    override fun updateDoctor(id: UUID, doctorCreateDto: DoctorCreateDto): DoctorResponseDto {
        val existing = doctorRepository.findById(id)
            .orElseThrow { NoSuchElementException("Doctor with ID $id not found") }

        val saved = doctorRepository.save(doctorMapper.toEntity(doctorCreateDto).apply {
            this.id = existing.id // Preserve the existing ID
            this.createdAt = existing.createdAt // Preserve the creation timestamp
        })
        return doctorMapper.toResponseDto(saved)
    }

}