package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.dto.SecurityUserDto
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServicePortImpl(
    private val userService: UserService,
): UserServicePort {

    companion object {
        var log: Logger = LoggerFactory.getLogger(UserServicePortImpl::class.java)
    }

    override fun create(dto: CreateUserDto) = userService.create(dto)

    override fun get(id: UUID) = userService.getById(id)

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.getByUsername(Username(username))
        val dto = SecurityUserDto(user, "ROLE_ADMIN")
        return dto
    }

}