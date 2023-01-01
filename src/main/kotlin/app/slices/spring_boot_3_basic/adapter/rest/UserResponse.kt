package app.slices.spring_boot_3_basic.adapter.rest

import app.slices.spring_boot_3_basic.domain.model.User
import java.util.*

data class UserResponse(
    val id: UUID,
    val username: String,
) {

    constructor(user: User) : this(
        user.id,
        user.username.value,
    )

}

