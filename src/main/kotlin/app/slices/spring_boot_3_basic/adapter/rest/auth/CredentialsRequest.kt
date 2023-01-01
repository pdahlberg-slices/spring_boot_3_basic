package app.slices.spring_boot_3_basic.adapter.rest.auth

data class CredentialsRequest(
    val username: String,
    val password: String,
)
