package app.slices.spring_boot_3_basic.adapter.rest

import app.slices.spring_boot_3_basic.domain.dto.UserDto

data class ListUsersResponse(
    val users: List<UserDto>,
)

