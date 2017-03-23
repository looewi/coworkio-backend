package com.coworkio.configuration

import com.coworkio.AUTH_PREFIX
import com.coworkio.configuration.properties.CorsProperties
import com.coworkio.util.security.AuthenticationFilter
import com.coworkio.util.security.provider.TokenAuthenticationProvider
import com.coworkio.util.security.provider.UsernameAndPasswordAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.Filter
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    private val BCRYPT_STRENGTH = 15

    @Autowired
    private lateinit var corsProperties: CorsProperties

    @Autowired
    private lateinit var usernameAndPasswordAuthenticationProvider: UsernameAndPasswordAuthenticationProvider

    @Autowired
    private lateinit var tokenAuthenticationProvider: TokenAuthenticationProvider

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
                ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()
                ?.authorizeRequests()
                ?.antMatchers("$AUTH_PREFIX/**")?.permitAll()
                ?.anyRequest()?.authenticated()?.and()
                ?.exceptionHandling()?.authenticationEntryPoint(authenticationEntryPoint())?.and()
//                ?.addFilterBefore(corsFilter(), ChannelProcessingFilter::class.java)
                ?.addFilterBefore(AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(usernameAndPasswordAuthenticationProvider)
                ?.authenticationProvider(tokenAuthenticationProvider)
    }

    @Bean
    open fun corsFilter(): Filter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.allowCredentials = true
        corsProperties.origins.forEach { x -> config.addAllowedOrigin(x) }
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")

        source.registerCorsConfiguration("/**", config)

        return CorsFilter(source)
    }

    @Bean
    open fun authenticationEntryPoint(): AuthenticationEntryPoint
            = AuthenticationEntryPoint { request, response, exception ->
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.message)
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(BCRYPT_STRENGTH)
    }
}