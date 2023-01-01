package app.slices.spring_boot_3_basic.domain.repository

import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import java.util.*

interface UserRepositoryPort {

    fun save(user: User): User

    fun findById(id: UUID): User?

    fun findByUsername(username: Username): User?

    fun findByState(states: Collection<UserState>): List<User>

}