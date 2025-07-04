package org.example.hospital.core.patient

import org.example.hospital.core.LocalToOffsetDateTimeConverter
import org.example.hospital.generated.api.model.PatientCreateDto
import org.example.hospital.generated.api.model.PatientResponseDto
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING
import org.mapstruct.MappingTarget

@Mapper(componentModel = SPRING, uses = [LocalToOffsetDateTimeConverter::class])
interface PatientMapper {

    fun toDto(patient: Patient): PatientResponseDto

    fun toEntity(dto: PatientCreateDto): Patient

    fun updateEntityFromDto(dto: PatientCreateDto, @MappingTarget patient: Patient)


}