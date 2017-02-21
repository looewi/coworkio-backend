package com.coworkio.controller

import com.coworkio.AUTH_PREFIX
import com.coworkio.controller.exception.BadRegistrationDataException
import com.coworkio.dto.UserDto
import com.coworkio.service.security.AuthenticationService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(AUTH_PREFIX)
open class AuthenticationController(@Autowired val authService: AuthenticationService) {

    private val log = LogFactory.getLog(this.javaClass)

    @RequestMapping(value = "/confirm", method = arrayOf(RequestMethod.PUT))
    fun confirm(id: String)
            = authService.confirm(id)

    @RequestMapping(value = "/register", method = arrayOf(RequestMethod.POST))
    fun register(@Valid @RequestBody user: UserDto, bindingResult: BindingResult)
            = when(bindingResult.hasErrors()) {
                false -> try {
                        authService.register(user)
                    } catch (e: BadCredentialsException) {
                        log.warn("Can't register user due to an error.", e)
                        throw BadRegistrationDataException()
                    }
                true -> throw BadRegistrationDataException()
            }
}