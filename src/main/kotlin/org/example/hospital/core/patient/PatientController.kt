package org.example.hospital.core.patient

import org.example.hospital.generated.api.PatientManagementApi
import org.example.hospital.generated.api.model.PatientCreateDto
import org.example.hospital.generated.api.model.PatientResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*

@RestController
class PatientController(
    private val patientService: PatientService
) : PatientManagementApi {
    override fun createPatient(patientCreateDto: PatientCreateDto): ResponseEntity<PatientResponseDto> {
        val dto = patientService.createPatient(patientCreateDto)
        return ResponseEntity.created(URI.create("/patients/${dto.id}")).body(dto)
    }

    override fun deletePatient(id: UUID): ResponseEntity<Unit> {
        patientService.deletePatient(id)
        return ResponseEntity.noContent().build()
    }

    override fun getAllPatients(): ResponseEntity<List<PatientResponseDto>> {
        return ResponseEntity.ok(patientService.getAllPatients())
    }

    override fun getPatientById(id: UUID): ResponseEntity<PatientResponseDto> {
        return ResponseEntity.ok(patientService.getPatientById(id))
    }

    override fun updatePatient(id: UUID, patientCreateDto: PatientCreateDto): ResponseEntity<PatientResponseDto> {
        return ResponseEntity.ok(patientService.updatePatient(id, patientCreateDto))
    }
}