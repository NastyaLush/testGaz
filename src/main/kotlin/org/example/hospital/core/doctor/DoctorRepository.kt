package org.example.hospital.core.doctor

import org.springframework.data.repository.CrudRepository
import java.util.*

interface DoctorRepository : CrudRepository<Doctor, UUID>
{
    override fun findAll(): List<Doctor>
}