package com.coworkio.util.security

import com.coworkio.EMAIL_CLAIM
import com.coworkio.EXPIRATION_TIME_MS
import com.coworkio.KEY
import com.coworkio.SERVER_URL
import com.coworkio.entity.domain.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
open class TokenBuilder {

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