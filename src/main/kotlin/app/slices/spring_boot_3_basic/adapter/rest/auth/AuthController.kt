package app.slices.spring_boot_3_basic.adapter.rest.auth

import app.slices.spring_boot_3_basic.domain.model.micro.RawPassword
import app.slices.spring_boot_3_basic.domain.model.micro.Username
import app.slices.spring_boot_3_basic.domain.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.util.*


class AuthController {
}

@RestController
@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:12345/"])
@RequestMapping("/auth")
class AuthenticationController(
    private val userService: UserService,
    private val jwtService: JwtService,
) {

    var log: Logger = LoggerFactory.getLogger(AuthenticationController::class.java)

    @PostMapping("/token", produces = [MediaType.TEXT_PLAIN_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun generateJwtToken(@RequestBody credentials: CredentialsRequest): ResponseEntity<String> {
        log.debug("Create a new user for dev environment... here's a UUID: ${UUID.randomUUID()}")
        val user = userService.getByUsernameAndVerify(Username(credentials.username), RawPassword(credentials.password))
        val token = jwtService.generateToken(user.username)
        return ok(token)
    }

}