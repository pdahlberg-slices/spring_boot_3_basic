package app.slices.spring_boot_3_basic.adapter.jpa

import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import app.slices.spring_boot_3_basic.domain.repository.UserRepositoryPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface UserEntityRepository : CrudRepository<app.slices.spring_boot_3_basic.adapter.jpa.UserEntity, UUID> {

    fun findByUsername(username: String): Optional<app.slices.spring_boot_3_basic.adapter.jpa.UserEntity>

    fun findByStateIn(states: Collection<UserState>): List<app.slices.spring_boot_3_basic.adapter.jpa.UserEntity>

}

@Repository
@Primary
class UserRepositoryJpaAdapter @Autowired constructor(
    private val repository: app.slices.spring_boot_3_basic.adapter.jpa.UserEntityRepository,
) : UserRepositoryPort {

    override fun save(user: User) = repository
        .save(app.slices.spring_boot_3_basic.adapter.jpa.UserEntity(user))
        .toUser()

    override fun findById(id: UUID): User? {
        return repository.findById(id)
            .orElse(null)
            ?.toUser()
    }

    override fun findByUsername(username: Username): User? {
        return repository.findByUsername(username.value)
            .orElse(null)
            ?.toUser()
    }

    override fun findByState(states: Collection<UserState>): List<User> {
        return repository.findByStateIn(states)
            .map { it.toUser() }
    }

}

