package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.TestHelpers.Companion.user
import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServicePortImplTest {

    private val userService = mockk<UserService>()
    private val userServicePort = UserServicePortImpl(userService)

    @Test
    fun create() {
        every { userService.create(any()) } answers { user(username = (firstArg() as CreateUserDto).username.value) }

        val result = userServicePort.create(CreateUserDto("name of user", "password"))

        assertThat(result.username.value).isEqualTo("name of user")
    }
}