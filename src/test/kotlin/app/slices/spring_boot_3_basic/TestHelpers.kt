package app.slices.spring_boot_3_basic

import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import app.slices.spring_boot_3_basic.domain.model.micro.Password
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import java.util.*

class TestHelpers {

    companion object {
        fun user(username: String = "name", state: UserState = UserState.active) = User(
            UUID.randomUUID(),
            Username(username),
            Password("encoded-pw"),
            state,
        )
    }

}