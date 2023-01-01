package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.adapter.rest.CreateUserRequest
import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.dto.UserDto
import app.slices.spring_boot_3_basic.domain.model.User

interface UserAdminServicePort {

    fun create(dto: CreateUserDto): User

    fun list(): List<User>

}