package app.slices.spring_boot_3_basic.adapter.rest.error

import java.util.*

class InvalidParamException(
    param: String,
) : RestException("Invalid param '$param'")
