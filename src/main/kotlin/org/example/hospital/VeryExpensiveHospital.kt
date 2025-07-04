package org.example.hospital

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class VeryExpensiveHospital

fun main(args: Array<String>) {
    runApplication<VeryExpensiveHospital>(*args)
}
