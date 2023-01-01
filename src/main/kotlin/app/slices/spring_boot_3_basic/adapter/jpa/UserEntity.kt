package app.slices.spring_boot_3_basic.adapter.jpa

import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import app.slices.spring_boot_3_basic.domain.model.micro.Password
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "user_tbl")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: UUID? = null,

    val username: String = "",
    val password: String = "",

    @Enumerated(EnumType.STRING)
    val state: UserState = UserState.active,
) {
    constructor(user: User) : this(
        id = user.id,
        username = user.username.value,
        password = user.password.encoded,
        state = user.state,
    )

    fun toUser() = User(
        this.id ?: throw IllegalStateException(),
        Username(username),
        Password(password),
        state,
    )

}