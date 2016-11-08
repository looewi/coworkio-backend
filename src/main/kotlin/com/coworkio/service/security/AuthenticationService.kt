package com.coworkio.service.security

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
open class AuthenticationService {

    fun login(username: String, password: String): Authentication {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}