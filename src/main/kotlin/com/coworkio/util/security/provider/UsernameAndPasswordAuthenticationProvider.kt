package com.coworkio.util.security.provider

import com.coworkio.service.security.AuthenticationService
import com.coworkio.util.security.TokenBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component

@Component
open class UsernameAndPasswordAuthenticationProvider(
        @Autowired val tokenBuilder: TokenBuilder,
        @Autowired val authService: AuthenticationService) : AuthenticationProvider{

    override fun authenticate(authentication: Authentication?): Authentication? {
        val email = authentication?.principal?.toString()
        val password = authentication?.credentials?.toString()
        if (email == null || password == null) {
            throw BadCredentialsException("Invalid user credentials.")
        }
        val auth = authService.login(email, password)
        val token = tokenBuilder.generateForAuthentication(auth)
        return PreAuthenticatedAuthenticationToken(token, null).apply { this.isAuthenticated = true }
    }

    override fun supports(authenticationClass: Class<*>?)
            = UsernamePasswordAuthenticationToken::class.java == authenticationClass
}