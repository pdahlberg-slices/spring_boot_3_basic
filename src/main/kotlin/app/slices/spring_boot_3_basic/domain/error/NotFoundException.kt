package app.slices.spring_boot_3_basic.domain.error

import java.util.*

class NotFoundException : Exception {
    constructor() : super()

    constructor(id: UUID) : this(id.toString())
    constructor(message: String) : super(message)

}