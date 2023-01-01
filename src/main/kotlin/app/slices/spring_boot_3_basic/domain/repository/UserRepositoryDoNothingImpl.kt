package app.slices.spring_boot_3_basic.domain.repository

import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserRepositoryDoNothingImpl : UserRepositoryPort {
    override fun save(user: User): User {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): User? {
        TODO("Not yet implemented")
    }

    override fun findByUsername(username: Username): User? {
        TODO("Not yet implemented")
    }

    override fun findByState(states: Collection<UserState>): List<User> {
        TODO("Not yet implemented")
    }

}