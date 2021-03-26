package com.wenance.challenge.application.exceptionhandler

import com.wenance.challenge.delivery.dto.ApiError
import com.wenance.challenge.domain.exception.RequestException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(RequestException::class)
    fun handleRequestException(ex: RequestException, request: WebRequest): ResponseEntity<ApiError> {
        val errorDetails = ApiError(ex.code, ex.message)
        logger.error("Exception thrown - CODE: ${ex.code}  MESSAGE: \"${ex.message}\" STATUS: ${ex.status}")
        return ResponseEntity(errorDetails, HttpStatus.valueOf(ex.status))
    }

    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        with(ex) {
            val firstError = bindingResult.fieldErrors.first()
            val apiError = ApiError(
                code = "validation.error",
                message = "${firstError.field} has an invalid value '${firstError.rejectedValue}'"
            )

            return ResponseEntity(apiError, status)
        }
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ApiError> {
        with(ex) {
            val firstError = constraintViolations.first()
            val apiError = ApiError(
                code = "request.param.error",
                message = "${firstError.propertyPath.last()} has an invalid value '${firstError.invalidValue}'"
            )

            return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
        }
    }
}
