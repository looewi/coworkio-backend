package com.coworkio.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.security.cors")
open class CorsProperties {

    var origins: Array<String> = emptyArray()
}