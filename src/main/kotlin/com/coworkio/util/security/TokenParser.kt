package com.coworkio.util.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*

@Component
open class TokenParser {

    fun getAuthenticationFromToken(token: String): Authentication {
        try {
            val claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).body
            returnIfTokenExpired(claims)

            val resultAuth = PreAuthenticatedAuthenticationToken(claims.subject, null)
            resultAuth.isAuthenticated = true
            return resultAuth
        } catch (ex: SignatureException) {
            throw BadCredentialsException("token data isn't concise")
        }
    }

    fun getConfirmationFromToken(token: String): String {
        try {
            val claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).body
            returnIfTokenExpired(claims)

            return claims.subject
        } catch (ex: SignatureException) {
            throw BadCredentialsException("token data isn't concise")
        }
    }

    private fun returnIfTokenExpired(claims: Claims) {
        if (claims.expiration.before(Date())) {
            throw BadCredentialsException("provided token has expired")
        }
    }
}