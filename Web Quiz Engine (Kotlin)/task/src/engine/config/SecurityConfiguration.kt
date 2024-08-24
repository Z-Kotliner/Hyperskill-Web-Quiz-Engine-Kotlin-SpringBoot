package engine.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain

/**
 *  Security configuration for authorizing endpoints
 *
 *  @author Omar Osman
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic(Customizer.withDefaults())
            .csrf { it.disable() }
            .authorizeHttpRequests { matcherRegistry ->
                matcherRegistry

                    .requestMatchers("/api/register", "/error/**").permitAll()
                    .requestMatchers("/actuator/shutdown").permitAll()
                    .requestMatchers("/**").authenticated()
                    .anyRequest().denyAll()
            }

        return http.build()
    }
}