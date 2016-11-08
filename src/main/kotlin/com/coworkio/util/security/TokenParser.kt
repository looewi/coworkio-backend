package com.coworkio.util.security

import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import java.security.SignatureException
import java.util.*
import kotlin.reflect.KClass


class TokenParser {

    fun getAuthenticationFromToken(token: String, targetClass: KClass<Any>): Any {
        try {
            val claim = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).body

            if (claim.expiration.before(Date())) {
                throw BadCredentialsException("provided token has expired")
            }

            when (targetClass.simpleName){
                "Authentication" -> {
                    val resultAuth = PreAuthenticatedAuthenticationToken(claim.subject, null)
                    resultAuth.isAuthenticated = true

                    return resultAuth
                }
                "String" -> return claim.subject
                else -> {
                    throw Exception("unexpected parsing target")
                }
            }
        } catch (ex: SignatureException) {
            throw BadCredentialsException("token data isn't concise")
        }
    }
}