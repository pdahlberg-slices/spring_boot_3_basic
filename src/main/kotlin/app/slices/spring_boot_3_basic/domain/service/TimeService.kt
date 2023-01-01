package app.slices.spring_boot_3_basic.domain.service

import org.springframework.stereotype.Service
import java.util.Date

@Service
class TimeService {

    fun now(): Date {
        return Date()
    }

}