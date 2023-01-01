package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.model.UserState.active
import app.slices.spring_boot_3_basic.domain.model.micro.Password
import app.slices.spring_boot_3_basic.domain.model.micro.RawPassword
import app.slices.spring_boot_3_basic.domain.repository.UserRepositoryPort
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    private val userRepository = mockk<UserRepositoryPort>()
    private val securityService = mockk<SecurityService>()
    private val userService = UserServiceImpl(userRepository, securityService)

    @Test
    fun create() {
        every { userRepository.save(any()) } answers { firstArg() }
        every { securityService.encode(any()) } answers { Password("encoded:${(firstArg() as RawPassword).decoded}") }

        val result = userService.create(CreateUserDto("name", "password"))

        assertThat(result.username.value).isEqualTo("name")
        assertThat(result.password.encoded).isEqualTo("encoded:password")
        assertThat(result.state).isEqualTo(active)
    }
}