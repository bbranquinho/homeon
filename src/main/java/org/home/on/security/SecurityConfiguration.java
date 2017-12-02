package org.home.on.security;

import org.home.on.utils.ResourcePaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    public static final String AUTH_USER = "ROLE_USER";

    public static final String AUTH_ADMIN = "ROLE_ADMIN";

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HeaderHandler headerHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                // Global Authority to OPTIONS (permit all).
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Public (permit all).
                //.antMatchers(ResourcePaths.PUBLIC_ROOT_PATH + ResourcePaths.ALL).permitAll()
//                .antMatchers(ResourcePaths.ROOT_PATH + ResourcePaths.ALL).permitAll()
                .antMatchers(ResourcePaths.ALL).permitAll()
                // User Authorities.
                .antMatchers(HttpMethod.GET, ResourcePaths.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.POST, ResourcePaths.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.PUT, ResourcePaths.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.DELETE, ResourcePaths.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
                // Permission Authorities.
                .antMatchers(HttpMethod.GET, ResourcePaths.PERMISSION_PATH).hasAnyAuthority(AUTH_ADMIN)
                .anyRequest().fullyAuthenticated().and()
                // Logout configuration.
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(ResourcePaths.LOGOUT_PATH)).logoutSuccessHandler(headerHandler).and()
                // CSRF configuration.
                .csrf().disable()
                .addFilterAfter(headerHandler, ChannelProcessingFilter.class);
    }

}
