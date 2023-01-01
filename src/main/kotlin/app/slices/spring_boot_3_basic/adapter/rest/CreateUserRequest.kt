package app.slices.spring_boot_3_basic.adapter.rest

import app.slices.spring_boot_3_basic.adapter.rest.error.InvalidParamException
import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto

class CreateUserRequest {
    var username: String? = null
    var password: String? = null

    fun getDto() = CreateUserDto(
        username ?: throw InvalidParamException("Username"),
        password ?: throw InvalidParamException("Password"),
    )
}
