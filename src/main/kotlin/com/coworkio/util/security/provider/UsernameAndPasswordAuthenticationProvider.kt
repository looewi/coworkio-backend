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
open class UsernameAndPasswordAuthenticationProvider : AuthenticationProvider{

    @Autowired
    private lateinit var tokenBuilder: TokenBuilder

    @Autowired
    private lateinit var authService: AuthenticationService

    override fun authenticate(authentication: Authentication?): Authentication? {
        val username = authentication?.principal?.toString()
        val password = authentication?.credentials?.toString()

        if (username == null || password == null) {
            throw BadCredentialsException("Invalid user credentials.")
        }

        val auth = authService.login(username, password)
        val token = tokenBuilder.generateForAuthentication(auth)

        val resultAuth = PreAuthenticatedAuthenticationToken(token, null)
        resultAuth.isAuthenticated = true

        return resultAuth
    }

    override fun supports(authenticationClass: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java == authenticationClass
    }
}