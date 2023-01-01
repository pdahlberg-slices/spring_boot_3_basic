package app.slices.spring_boot_3_basic.domain.service

import app.slices.spring_boot_3_basic.domain.model.micro.Password
import app.slices.spring_boot_3_basic.domain.model.micro.RawPassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecurityService @Autowired constructor(
    private val passwordEncoder: PasswordEncoder,
) {

    fun encode(raw: RawPassword): Password {
        val encoded = passwordEncoder.encode(raw.decoded)
        return Password(encoded)
    }

    fun matches(raw: RawPassword, password: Password) =
        passwordEncoder.matches(raw.decoded, password.encoded)

}