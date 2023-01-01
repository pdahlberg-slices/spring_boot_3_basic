package app.slices.spring_boot_3_basic.domain.model.micro

data class Username(
    val value: String,
) {

    companion object {
        const val MIN_LENGTH = 4
        const val MAX_LENGTH = 32
    }

    init {
        require(value.length >= MIN_LENGTH) { "Username should be min $MIN_LENGTH characters long" }
        require(value.length <= MAX_LENGTH) { "Username should be max $MAX_LENGTH characters long" }
    }

}


