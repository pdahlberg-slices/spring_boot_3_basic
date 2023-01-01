package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.model.User
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

interface UserServicePort : UserDetailsService {

    fun create(dto: CreateUserDto): User

    fun get(id: UUID): User

}