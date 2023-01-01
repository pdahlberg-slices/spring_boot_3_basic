package app.slices.spring_boot_3_basic.adapter.rest.admin

import app.slices.spring_boot_3_basic.adapter.rest.CreateUserRequest
import app.slices.spring_boot_3_basic.adapter.rest.CreateUserResponse
import app.slices.spring_boot_3_basic.adapter.rest.ListUsersResponse
import app.slices.spring_boot_3_basic.domain.dto.UserDto
import app.slices.spring_boot_3_basic.domain.service.UserAdminServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/admin/users")
class UserAdminController @Autowired constructor(
    val userAdminService: UserAdminServicePort,
) {

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Validated @RequestBody createUserRequest: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        val user = userAdminService.create(createUserRequest.getDto())
        return ok(CreateUserResponse(user.id))
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun list(): ResponseEntity<ListUsersResponse> {
        val dtos = userAdminService.list().map {
            UserDto(it.username)
        }

        return ok(ListUsersResponse(dtos))
    }

}