package app.slices.spring_boot_3_basic.domain.model

enum class UserState {

    active,
    inactive,
    deleted;

    companion object {
        val admin = listOf(active, inactive, deleted)

        fun fromString(value: String): UserState? {
            return UserState.values().find { it.toString() == value.lowercase() }
        }
    }

}
