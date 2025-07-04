package org.example.hospital.core.patient

import org.example.hospital.generated.api.model.PatientCreateDto
import org.example.hospital.generated.api.model.PatientResponseDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PatientService(
    private val patientRepository: PatientRepository,
    private val patientMapper: PatientMapper
) {

    @Transactional
    fun createPatient(patientCreateDto: PatientCreateDto): PatientResponseDto {
        val patient = patientMapper.toEntity(patientCreateDto)
        return patientMapper.toDto(patientRepository.save(patient))
    }

    @Transactional
    fun updatePatient(id: UUID, patientCreateDto: PatientCreateDto): PatientResponseDto {
        val existingPatient = patientRepository.findById(id)
            .orElseThrow { RuntimeException("Patient not found") }

        patientMapper.updateEntityFromDto(patientCreateDto, existingPatient)
        return patientMapper.toDto(patientRepository.save(existingPatient))
    }

    fun getPatientById(id: UUID): PatientResponseDto {
        val patient = patientRepository.findById(id)
            .orElseThrow { RuntimeException("Patient not found") }
        return patientMapper.toDto(patient)
    }

    fun getAllPatients(): List<PatientResponseDto> {
        return patientRepository.findAll().map(patientMapper::toDto)
    }

    @Transactional
    fun deletePatient(id: UUID) {
        if (!patientRepository.existsById(id)) {
            throw RuntimeException("Patient not found")
        }
        patientRepository.deleteById(id)
    }
}