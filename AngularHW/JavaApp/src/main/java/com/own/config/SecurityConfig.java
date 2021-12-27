package com.own.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.own.additional.CustomUserDetails;
import com.own.entity.User;
import com.own.repository.UserRepository;
import com.own.service.CustomUserDetailsService;
//import com.own.service.PBKDF2EncoderCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("pass")
//                .roles("ADMIN");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(getCorsConfigurationSource()).and()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "rest/v1/positions").permitAll()
                .antMatchers(HttpMethod.GET, "rest/v1/categories").permitAll()
                .antMatchers(HttpMethod.POST, "rest/v1/categories").hasAuthority("EDIT_CATALOG")
                .antMatchers(HttpMethod.POST, "rest/v1/positions").hasAuthority("ADDWARE")
                .antMatchers(HttpMethod.POST, "rest/v1/positions/delete").hasAuthority("EDIT_CATALOG")
                .antMatchers(HttpMethod.PUT, "rest/v1/positions").hasAuthority("EDIT_CATALOG")

                .antMatchers(HttpMethod.GET, "rest/v1/users/all").permitAll()
                .antMatchers(HttpMethod.POST, "rest/v1/users/add").permitAll()
                .antMatchers(HttpMethod.PUT, "rest/v1/users/add").hasAuthority("EDIT_USER")

                .antMatchers(HttpMethod.POST, "rest/v1/discounts/ware").hasAuthority("EDIT_CATALOG")
                .antMatchers(HttpMethod.POST, "rest/v1/discounts/user").hasAuthority("EDIT_USER")
                .antMatchers(HttpMethod.POST, "rest/v1/discounts/ware/delete").hasAuthority("EDIT_CATALOG")
                .and().csrf().disable()
                .headers().frameOptions().disable()
                .and().formLogin().successHandler(successHandler()).failureHandler(failureHandler());
    }

    public CorsConfigurationSource getCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

                httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
                MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
                ObjectMapper mapper = messageConverter.getObjectMapper();

                httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                User user = userRepository.findByUsername(userDetails.getUsername()).get();

                LOGGER.info(userDetails.getUsername() + " is connected");

                PrintWriter writer = httpServletResponse.getWriter();

                mapper.writeValue(writer, user);

                writer.flush();
            }


        };
    }

    @CrossOrigin(origins = "http://localhost:4200")
    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                System.out.println(httpServletRequest.getHeader("Content-Type"));
                System.out.println(httpServletRequest.getAuthType());
                System.out.println(httpServletRequest.getUserPrincipal());
                System.out.println(httpServletRequest.getServletContext().getAttributeNames());
                System.out.println(httpServletRequest.isSecure());
                httpServletResponse.getWriter().append("Authentication failure");
                httpServletResponse.setStatus(401);
            }
        };
    }


}
