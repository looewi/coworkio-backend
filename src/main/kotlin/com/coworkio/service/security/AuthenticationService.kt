package com.coworkio.service.security

import com.coworkio.dto.UserDto
import com.coworkio.dto.mapper.UserDtoMapper
import com.coworkio.entity.domain.User
import com.coworkio.service.domain.UserService
import com.coworkio.util.security.TokenBuilder
import com.coworkio.util.security.TokenParser
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.util.*

@Service
@Qualifier("authenticationService")
open class AuthenticationService(
        @Autowired val tokenBuilder: TokenBuilder,
        @Autowired val tokenParser: TokenParser,
        @Autowired val userService: UserService,
        @Autowired val passwordEncoder: PasswordEncoder) {

    private val log = LogFactory.getLog(this.javaClass)

    fun login(email: String, password: String): Authentication
            = when(passwordEncoder.matches(password, userService.findByEmail(email)?.password)) {
                true -> UsernamePasswordAuthenticationToken(email, null)
                false ->throw BadCredentialsException("Username and password are not match.")
            }

    fun register(user: UserDto) {
        if(userService.exists(user)) {
            throw BadCredentialsException("provided user already exists")
        }
        val userDomain = UserDtoMapper().toDomain(user)
        userService.saveOrUpdate(userDomain)
        sendConfirmationEmail(userDomain)
    }

    fun confirm(encodedToken: String): Boolean {
        val token = String(Base64.getDecoder().decode(encodedToken), Charset.forName(UTF8))

        try {
            val username = tokenParser.getConfirmationFromToken(token)
            //TODO: update account_confirmed field
        } catch (ex: BadCredentialsException) {
            log.error("Unable to confirm registration.", ex)
            return false
        }

        return true
    }

    private fun sendConfirmationEmail(user: User) {
        val token = tokenBuilder.generateForConfirmation(user)
        val encodedToken = Base64.getEncoder().encodeToString(token.toByteArray(charset = Charset.forName(UTF8)))

        //TODO: send email
    }

    private fun encodePassword(password: String):String
            = passwordEncoder.encode(password)

    companion object {
        private val UTF8 = "UTF-8"
    }
}