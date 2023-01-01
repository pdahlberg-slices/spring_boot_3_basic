package app.slices.spring_boot_3_basic.adapter.jpa

import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.service.UserAdminServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class UserRepositoryJpaTest {

    @Container
    private val postgresqlContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:15.1-alpine")
        .withDatabaseName("foo")
        .withUsername("foo")
        .withPassword("secret")

    @Autowired
    private lateinit var userAdminServiceImpl: UserAdminServiceImpl

    @Autowired
    private lateinit var userRepositoryJpaAdapter: UserRepositoryJpaAdapter

    @Test
    fun `Admin creates user`() {
        val dto = CreateUserDto("name-1", "raw password")

        val result = userAdminServiceImpl.create(dto)

        assertThat(result.username.value).isEqualTo("name-1")
        val findById = userRepositoryJpaAdapter.findById(result.id)
        assertThat(findById).isNotNull
    }

}
