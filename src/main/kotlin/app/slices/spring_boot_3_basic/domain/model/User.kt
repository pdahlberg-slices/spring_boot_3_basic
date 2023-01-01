package app.slices.spring_boot_3_basic.domain.model

import app.slices.spring_boot_3_basic.domain.model.micro.Password
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import java.util.UUID

data class User(
    val id: UUID,
    val username: Username,
    val password: Password,
    val state: UserState,
)
