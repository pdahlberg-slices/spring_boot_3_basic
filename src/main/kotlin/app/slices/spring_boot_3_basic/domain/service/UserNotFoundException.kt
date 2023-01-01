package app.slices.spring_boot_3_basic.domain.service

import java.util.*

class UserNotFoundException : Exception {
    constructor() : super()

    constructor(id: UUID) : this(id.toString())
    constructor(message: String) : super(message)

}