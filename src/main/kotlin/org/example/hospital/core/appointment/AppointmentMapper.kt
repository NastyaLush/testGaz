package org.example.hospital.core.appointment

import org.example.hospital.core.LocalToOffsetDateTimeConverter
import org.example.hospital.core.OffsetToLocalDateTimeConverter
import org.example.hospital.generated.api.model.AppointmentCreateDto
import org.example.hospital.generated.api.model.AppointmentResponseDto
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING
import org.mapstruct.MappingTarget

@Mapper(
    componentModel = SPRING,
    uses = [LocalToOffsetDateTimeConverter::class, OffsetToLocalDateTimeConverter::class]
)
interface AppointmentMapper {

    fun toDto(appointment: Appointment): AppointmentResponseDto
    fun toEntity(dto: AppointmentCreateDto): Appointment
    fun updateEntityFromDto(dto: AppointmentCreateDto, @MappingTarget appointment: Appointment)
}