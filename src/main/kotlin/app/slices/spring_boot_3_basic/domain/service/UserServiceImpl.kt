package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import app.slices.spring_boot_3_basic.domain.model.micro.RawPassword
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import app.slices.spring_boot_3_basic.domain.repository.UserRepositoryPort
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepositoryPort: UserRepositoryPort,
    private val securityService: SecurityService,
): UserService {

    companion object {
        var log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }

    override fun getById(id: UUID): User = userRepositoryPort.findById(id) ?: throw UserNotFoundException(id)
    override fun getByUsername(username: Username) = userRepositoryPort.findByUsername(username)
        ?: throw UserNotFoundException(username.value)

    override fun getByUsernameAndVerify(username: Username, rawPassword: RawPassword): User {
        val user = getByUsername(username)
        val matchingPasswords = securityService.matches(rawPassword, user.password)
        require(matchingPasswords) { "Invalid password" }
        return user
    }

    override fun getByState(states: Collection<UserState>): List<User> {
        return userRepositoryPort.findByState(states)
    }

    override fun save(user: User): User = userRepositoryPort.save(user)

    override fun create(dto: CreateUserDto): User {
        val password = securityService.encode(dto.password)
        val user = User(UUID.randomUUID(), dto.username, password, UserState.active)
        save(user)
        return user
    }

}
