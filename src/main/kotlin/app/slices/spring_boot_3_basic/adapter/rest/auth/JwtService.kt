package app.slices.spring_boot_3_basic.adapter.rest.auth

import app.slices.spring_boot_3_basic.domain.model.micro.Username
import app.slices.spring_boot_3_basic.domain.service.TimeService
import app.slices.spring_boot_3_basic.domain.service.UserServicePort
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


@Service
class JwtService @Autowired constructor(
    private val userService: UserServicePort,
    private val timeService: TimeService,
) {

    companion object {
        val JWT_TOKEN_VALIDITY_5H = 5 * 60 * 60 * 1000
        val key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }

    fun generateToken(username: Username): String {
        return Jwts.builder()
            .setClaims(mapOf<String, Any>())
            .setSubject(username.value)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_5H))
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username: String = getUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun getUsername(token: String): String {
        return getClaimsFromToken(token).subject
    }

    fun getExpirationDate(token: String): Date {
        return getClaimsFromToken(token).expiration
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDate(token)
        return expiration.before(timeService.now())
    }

    private fun getClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun getSecurityUser(jwtToken: String): UserDetails? {
        val username = getUsername(jwtToken)
        if (username.isNotEmpty()) {
            val securityUser = userService.loadUserByUsername(username)
            if (validateToken(jwtToken, securityUser)) {
                return securityUser
            }
        }

        return null
    }

}