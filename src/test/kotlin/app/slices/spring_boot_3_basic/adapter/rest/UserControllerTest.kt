package app.slices.spring_boot_3_basic.adapter.rest

import app.slices.spring_boot_3_basic.TestHelpers.Companion.user
import app.slices.spring_boot_3_basic.adapter.rest.auth.JwtService
import app.slices.spring_boot_3_basic.adapter.rest.config.RestSecurityConfig
import app.slices.spring_boot_3_basic.domain.dto.SecurityUserDto
import app.slices.spring_boot_3_basic.domain.service.UserServicePort
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.security.test.*
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [UserController::class])
@ContextConfiguration(classes = [app.slices.spring_boot_3_basic.App::class, RestSecurityConfig::class, ControllerTestConfig::class])
class UserControllerTest {

    @MockkBean
    lateinit var jwtService: JwtService

    @MockkBean
    lateinit var userServicePort: UserServicePort

    @Autowired
    lateinit var mvc: MockMvc

    @BeforeEach
    fun beforeEach() {
        every { jwtService.getSecurityUser(any()) } returns SecurityUserDto("name", "pw", "ROLE_USER", true)
    }

    @Test
    fun get() {
        val uuid = UUID.randomUUID();

        every { userServicePort.get(any()) } returns user()

        val request = get("/users/$uuid")
            .header(HttpHeaders.AUTHORIZATION, "Bearer TEST")

        mvc.perform(request)
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.username").value("name"))
    }

}