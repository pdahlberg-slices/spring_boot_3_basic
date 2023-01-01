package app.slices.spring_boot_3_basic.adapter.rest.error

import java.util.*

open class RestException(
    val publicMsg: String,
    val privateMsg: String? = null,
) : Exception(publicMsg)
