package com.coworkio.util.security

import com.coworkio.AUTHENTICATE_URL
import com.coworkio.HTTP_POST
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.util.UrlPathHelper
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(val authenticationManager: AuthenticationManager?) : GenericFilterBean() {

    private val log = LogFactory.getLog(this.javaClass)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val email = httpRequest.getParameter(EMAIL_PARAMETER)
        val password = httpRequest.getParameter(PASSWORD_PARAMETER)
        val token = httpRequest.getHeader(TOKEN_HEADER)

        val resourcePath = UrlPathHelper().getPathWithinApplication(httpRequest)

        try {
            if (postToAuthenticate(httpRequest, resourcePath)) {
                log.debug("Trying to authenticate user $email by X-Auth-Username method.")

                processEmailPasswordAuthentication(httpResponse, email, password)
                return
            }

            if (token != null) {
                log.debug("Trying to authenticate user by X-Auth-Token method (Token: $token).")
                processTokenAuthentication(token)
            }

            log.debug("Passing request down the filter chain.")

            chain?.doFilter(request, response)
        } catch (ex: InternalAuthenticationServiceException) {
            SecurityContextHolder.clearContext()
            log.error("Internal Authentication error occurred.", ex)
            httpResponse.sendError((HttpServletResponse.SC_INTERNAL_SERVER_ERROR))
        } catch (ex: AuthenticationException) {
            SecurityContextHolder.clearContext()
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.message)
        }
    }

    private fun postToAuthenticate(request: HttpServletRequest, path: String)
            = AUTHENTICATE_URL.equals(path, true) && HTTP_POST.equals(request.method, true)

    private fun processEmailPasswordAuthentication(response: HttpServletResponse, username: String?, password: String?) {
        val resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(username, password)
        SecurityContextHolder.getContext().authentication = resultOfAuthentication

        response.status = HttpServletResponse.SC_OK

        val tokenResponse = TokenResponse(resultOfAuthentication.principal.toString())
        val tokenJsonResponse = ObjectMapper().writeValueAsString(tokenResponse)

        response.addHeader("Content-Type", "application/json")
        response.writer.print(tokenJsonResponse)
    }

    private fun processTokenAuthentication(token: String?)
            = apply{ SecurityContextHolder.getContext().authentication = tryToAuthenticateWithToken(token) }

    private fun tryToAuthenticateWithUsernameAndPassword(username: String?, password: String?)
            = tryToAuthenticate(UsernamePasswordAuthenticationToken(username, password))

    private fun tryToAuthenticateWithToken(token: String?)
            = tryToAuthenticate(PreAuthenticatedAuthenticationToken(token, null))

    private fun tryToAuthenticate(requestAuthentication: Authentication): Authentication {
        val responseAuthentication = authenticationManager?.authenticate(requestAuthentication)

        if (responseAuthentication == null || !responseAuthentication.isAuthenticated) {
            throw InternalAuthenticationServiceException("Unable to authenticate user for provided credentials")
        }
        log.debug("User successfully authenticated.")
        return responseAuthentication
    }

    companion object {
        private val EMAIL_PARAMETER = "email"
        private val PASSWORD_PARAMETER = "password"
        private val TOKEN_HEADER = "X-Auth-Token"
    }
}

private data class TokenResponse(val token: String)