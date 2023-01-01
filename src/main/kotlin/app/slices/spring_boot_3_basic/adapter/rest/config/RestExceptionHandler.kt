package app.slices.spring_boot_3_basic.adapter.rest.config

import app.slices.spring_boot_3_basic.adapter.rest.error.InvalidParamException
import app.slices.spring_boot_3_basic.adapter.rest.error.RestException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.IllegalArgumentException

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    var log: Logger = LoggerFactory.getLogger(RestExceptionHandler::class.java)

    @ExceptionHandler(value = [InvalidParamException::class])
    protected fun handleInvalidParam(ex: InvalidParamException, request: WebRequest): ResponseEntity<Any>? {
        val bodyOfResponse = """{"error":"${ex.message}"}"""

        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request,
        )
    }

    @ExceptionHandler(value = [RestException::class])
    protected fun handleTheRest(ex: RestException, request: WebRequest): ResponseEntity<Any>? {
        log.debug("Private error: ${ex.privateMsg}")
        val bodyOfResponse = """{"error":"${ex.publicMsg}"}"""

        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request,
        )
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    protected fun handleModelDataError(ex: IllegalArgumentException, request: WebRequest): ResponseEntity<Any>? {
        val bodyOfResponse = """{"error":"${ex.message}"}"""

        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request,
        )
    }

    @ExceptionHandler(value = [Exception::class])
    protected fun handleEverythingElse(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        log.error("Private error: ${ex.message}")
        val bodyOfResponse = """{"error":"N/A"}"""

        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request,
        )
    }

}
