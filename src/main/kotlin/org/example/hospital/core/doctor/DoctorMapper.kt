package org.example.hospital.core.doctor

import org.example.hospital.core.LocalToOffsetDateTimeConverter
import org.example.hospital.core.OffsetToLocalDateTimeConverter
import org.example.hospital.generated.api.model.DoctorCreateDto
import org.example.hospital.generated.api.model.DoctorResponseDto
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING, uses = [LocalToOffsetDateTimeConverter::class, OffsetToLocalDateTimeConverter::class])
interface DoctorMapper {
    fun toResponseDtoList(doctors: List<Doctor>): List<DoctorResponseDto>
    fun toResponseDto(doctor: Doctor): DoctorResponseDto
    fun toEntity(doctorCreateDto: DoctorCreateDto): Doctor

}