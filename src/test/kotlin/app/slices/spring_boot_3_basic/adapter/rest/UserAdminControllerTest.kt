package app.slices.spring_boot_3_basic.adapter.rest

import app.slices.spring_boot_3_basic.TestHelpers.Companion.user
import app.slices.spring_boot_3_basic.adapter.rest.admin.UserAdminController
import app.slices.spring_boot_3_basic.adapter.rest.auth.JwtService
import app.slices.spring_boot_3_basic.adapter.rest.config.RestSecurityConfig
import app.slices.spring_boot_3_basic.domain.dto.SecurityUserDto
import app.slices.spring_boot_3_basic.domain.service.UserAdminServicePort
import app.slices.spring_boot_3_basic.domain.service.UserServicePort
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.test.*
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*


@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [UserAdminController::class])
@ContextConfiguration(classes = [app.slices.spring_boot_3_basic.App::class, RestSecurityConfig::class, ControllerTestConfig::class])
class UserAdminControllerTest {

    @MockkBean
    lateinit var jwtService: JwtService

    @MockkBean
    lateinit var userServicePort: UserServicePort

    @MockkBean
    lateinit var userAdminServicePort: UserAdminServicePort

    @Autowired
    lateinit var mvc: MockMvc

    @BeforeEach
    fun beforeEach() {
        every { jwtService.getSecurityUser("ADMIN_TOKEN") } returns SecurityUserDto("name", "pw", "ROLE_ADMIN", true)
        every { jwtService.getSecurityUser("USER_TOKEN") } returns SecurityUserDto("name", "pw", "ROLE_USER", true)
    }

    @Test
    fun create() {
        val user = user()
        every { userAdminServicePort.create(any()) } returns user

        val request = post("/admin/users")
            .header(HttpHeaders.AUTHORIZATION, "Bearer ADMIN_TOKEN")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("""
                {
                    "username": "user",
                    "password": "password"
                }
            """.trimIndent())

        mvc.perform(request)
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(user.id.toString()))
    }

    @Test
    fun list() {
        every { userAdminServicePort.list() } returns listOf(user())

        val request = get("/admin/users")
            .header(HttpHeaders.AUTHORIZATION, "Bearer ADMIN_TOKEN")

        mvc.perform(request)
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.users").exists())
    }

    @Test
    fun `User role receives an error when accessing the admin controller`() {
        every { userAdminServicePort.list() } returns listOf(user())

        val request = get("/admin/users")
            .header(HttpHeaders.AUTHORIZATION, "Bearer USER_TOKEN")

        mvc.perform(request)
            .andExpect(status().isForbidden)
    }

    @Test
    fun `Return error when no role is present receives an error when accessing the admin controller`() {
        every { userAdminServicePort.list() } returns listOf(user())

        val request = get("/admin/users")

        mvc.perform(request)
            .andExpect(status().isForbidden)
    }

}