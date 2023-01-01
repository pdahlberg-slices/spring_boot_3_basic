package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.domain.dto.CreateUserDto
import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import app.slices.spring_boot_3_basic.domain.model.micro.RawPassword
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import java.util.UUID

interface UserService {

    fun getById(id: UUID): User

    fun getByUsername(username: Username): User

    fun getByUsernameAndVerify(username: Username, rawPassword: RawPassword): User

    fun getByState(states: Collection<UserState>): List<User>

    fun save(user: User): User

    fun create(dto: CreateUserDto): User

}
