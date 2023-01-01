package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.adapter.rest.CreateUserRequest
import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.dto.UserDto
import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserAdminServiceImpl @Autowired constructor(
    private val userService: UserService,
): UserAdminServicePort {

    companion object {
        var log: Logger = LoggerFactory.getLogger(UserAdminServiceImpl::class.java)
    }

    override fun create(dto: CreateUserDto): User = userService.create(dto)

    override fun list(): List<User> {
        return userService.getByState(UserState.admin)
    }

}