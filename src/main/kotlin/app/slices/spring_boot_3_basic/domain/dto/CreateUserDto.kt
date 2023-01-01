package app.slices.spring_boot_3_basic.domain.dto

import app.slices.spring_boot_3_basic.domain.model.micro.RawPassword
import app.slices.spring_boot_3_basic.domain.model.micro.Username

data class CreateUserDto(
    val username: Username,
    val password: RawPassword,
) {

    constructor(username: String, rawPassword: String) : this(
        Username(username),
        RawPassword(rawPassword),
    )

}
