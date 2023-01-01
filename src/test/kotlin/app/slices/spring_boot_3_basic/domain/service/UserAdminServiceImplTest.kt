package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.domain.repository.UserRepositoryPort
import io.mockk.mockk
import org.junit.jupiter.api.Test

import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserAdminServiceImplTest {

    private val userService = mockk<UserService>()
    private val userAdminService = UserAdminServiceImpl(userService)

    @Test
    fun list() {
        every { userService.getByState(any()) } returns listOf()

        val result = userAdminService.list()

        assertThat(result).isEmpty()
    }
}