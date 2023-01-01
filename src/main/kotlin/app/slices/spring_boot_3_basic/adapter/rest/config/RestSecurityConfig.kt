package app.slices.spring_boot_3_basic.adapter.rest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class RestSecurityConfig(
    private val jwtRequestFilter: JwtRequestFilter,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests {
                it.requestMatchers("/auth/token").permitAll()
                    .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().denyAll()
            }
            .sessionManagement {
                it.sessionCreationPolicy(STATELESS)
                    .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            }.build()
    }

}

