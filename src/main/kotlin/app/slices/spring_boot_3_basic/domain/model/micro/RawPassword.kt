package app.slices.spring_boot_3_basic.domain.model.micro

import app.slices.spring_boot_3_basic.adapter.rest.error.RestException

data class RawPassword(
    val decoded: String,
) {

    companion object {
        const val MIN_LENGTH = 8
        const val MAX_LENGTH = 512
    }

    init {
        require(decoded.length >= MIN_LENGTH) { "Password should be min $MIN_LENGTH characters long" }
        require(decoded.length <= MAX_LENGTH) { "Password should be max $MAX_LENGTH characters long" }
    }

}