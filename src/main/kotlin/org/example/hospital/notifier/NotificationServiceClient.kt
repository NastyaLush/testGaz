package org.example.hospital.notifier

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "notifier", url = "\${notification.service.url:https://notification-api.example.com}")
interface NotificationServiceClient {

    @PostMapping("/api/v1/notify")
    fun notify(@RequestBody message: NotificationRequest): Unit
}