package org.example.hospital.core.patient

import org.springframework.data.repository.CrudRepository
import java.util.*

interface PatientRepository : CrudRepository<Patient, UUID> {
}