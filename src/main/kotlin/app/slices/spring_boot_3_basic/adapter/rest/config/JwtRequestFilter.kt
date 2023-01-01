package app.slices.spring_boot_3_basic.adapter.rest.config

import app.slices.spring_boot_3_basic.adapter.rest.auth.JwtService
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*


@Component
class JwtRequestFilter @Autowired constructor(
    private val jwtService: JwtService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val headerToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if(headerToken != null) {
            if (headerToken.startsWith("Bearer ")) {
                val jwtToken = headerToken.substring(7)
                try {
                    val contextAuthentication = SecurityContextHolder.getContext().authentication
                    if(contextAuthentication == null) {
                        val securityUser = jwtService.getSecurityUser(jwtToken)
                        if(securityUser != null) {
                            val authToken = UsernamePasswordAuthenticationToken(securityUser, null, securityUser.authorities)
                            authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                            SecurityContextHolder.getContext().authentication = authToken
                        }
                    }
                } catch (e: IllegalArgumentException) {
                    logger.error("Unable to fetch JWT Token")
                } catch (e: ExpiredJwtException) {
                    logger.error("JWT Token is expired")
                } catch (e: Exception) {
                    logger.error(e.message)
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String")
            }
        }

        chain.doFilter(request, response);
    }

}
