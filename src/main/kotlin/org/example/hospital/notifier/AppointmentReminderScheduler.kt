package org.example.hospital.notifier

import org.example.hospital.core.appointment.AppointmentRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AppointmentReminderScheduler(
    private val appointmentRepository: AppointmentRepository,
    private val notificationClient: NotificationServiceClient
) {

    // Runs every 15 minutes
    @Scheduled(fixedRate = 60 * 1000)
    fun sendAppointmentReminders() {
        val now = LocalDateTime.now()

        // Fetch appointments in both time windows
        val twoHourAppointments = appointmentRepository.findByDate(now.plusHours(2))
        val twentyFourHourAppointments = appointmentRepository.findByDate(now.plusHours(24))

        // Send notifications
        twoHourAppointments.forEach {
            notificationClient.notify(
                NotificationRequest(
                    "Reminder: Your appointment is in 2 hours with ${it.doctorName}",
                    it.email
                )
            )
        }

        twentyFourHourAppointments.forEach {
            notificationClient.notify(
                NotificationRequest(
                    "Reminder: Your appointment is in 24 hours with ${it.doctorName}",
                    it.email
                )
            )
        }
    }
}