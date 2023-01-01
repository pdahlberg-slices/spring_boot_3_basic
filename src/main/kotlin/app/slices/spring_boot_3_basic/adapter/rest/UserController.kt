package app.slices.spring_boot_3_basic.adapter.rest

import app.slices.spring_boot_3_basic.domain.dto.UserDto
import app.slices.spring_boot_3_basic.domain.service.UserServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
    val userService: UserServicePort,
) {

    @PostMapping(produces = [APPLICATION_JSON_VALUE], consumes = [APPLICATION_JSON_VALUE])
    fun create(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse {
        val user = userService.create(createUserRequest.getDto())
        return CreateUserResponse(user.id)
    }

    @GetMapping(path = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    fun get(@PathVariable id: UUID): ResponseEntity<UserResponse> {
        val user = userService.get(id)
        return ok(UserResponse(user))
    }

}