package app.slices.spring_boot_3_basic.domain.dto

import app.slices.spring_boot_3_basic.domain.model.User
import app.slices.spring_boot_3_basic.domain.model.UserState
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class SecurityUserDto(
    private val username: String,
    private val password: String,
    private val role: String,
    private val enabled: Boolean,
) : UserDetails {

    constructor(user: User, role: String) : this(
        user.username.value,
        user.password.encoded,
        role,
        user.state == UserState.active,
    )

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role))
    }

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled() = enabled
}