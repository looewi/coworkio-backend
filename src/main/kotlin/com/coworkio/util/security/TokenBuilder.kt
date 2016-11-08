package com.coworkio.util.security

import com.coworkio.entity.domain.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import java.util.*

class TokenBuilder {

    fun generateForAuthentication(authentication: Authentication): String
            = Jwts.builder()
                .setIssuer(SERVER_URL)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .setSubject(authentication.principal.toString())
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact()

    fun generateForConfirmation(user: User): String
            = Jwts.builder()
                .setIssuer(SERVER_URL)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .setSubject(user.firstName + user.lastName)
                .setIssuedAt(Date())
                .claim(EMAIL_CLAIM, user.email)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact()
}