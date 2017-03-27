package com.coworkio.util.security.provider

import com.coworkio.util.security.TokenParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component

@Component
open class TokenAuthenticationProvider(@Autowired val tokenParser: TokenParser) : AuthenticationProvider{

    override fun authenticate(authentication: Authentication?): Authentication? {
        val token = authentication?.principal
        if (token == null || (token as String).isEmpty()) {
            throw BadCredentialsException("invalid token provided")
        }
        return tokenParser.getAuthenticationFromToken(token)
    }

    override fun supports(authenticationClass: Class<*>?)
            = PreAuthenticatedAuthenticationToken::class.java == authenticationClass
}