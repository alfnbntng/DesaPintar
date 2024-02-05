package com.desapintar.DesaPintar.Security;

import com.desapintar.DesaPintar.Security.JwtAuthenticationEntryPoint;
import com.desapintar.DesaPintar.Security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // API controller
            "/login", "/register",
            // perangkat desa
            "/desa-pintar/api/perangkat/all",
            "/desa-pintar/api/perangkat/ById{id}",
            // keluarga
            "/desa-pintar/api/keluarga/all",
            "/desa-pintar/api/keluarga/ById{id}",
            // bantuan
            "/desa-pintar/api/bantuan/all",
            "/desa-pintar/api/bantuan/ById{id}",
            "/desa-pintar/api/bantuan/getPenerimaByBantuan/{jenisBantuanId}",
            // galeri
            "/desa-pintar/api/galeri/ById{id}",
            "/desa-pintar/api/galeri/all",
            // penerima bantuan
            "/desa-pintar/api/penerima-bantuan/all",
            "/desa-pintar/api/penerima-bantuan/ById{id}",
            // rencana-kerja
            "/desa-pintar/api/rencana-kerja/all",
            "/desa-pintar/api/rencana-kerja/ById{id}",
            // pemasukan
            "/desa-pintar/api/pemasukan/list",
            "/desa-pintar/api/pemasukan/{id}",
            // pengeluaran
            "/desa-pintar/api/pengeluaran/list",
            "/desa-pintar/api/pengeluaran/detail/{id}",
            // saldo
            "/desa-pintar/api/saldo/check",



    };

    private static final String[] AUTH_AUTHORIZATION = {
            "desa-pintar/api/perangkat/**",
            "desa-pintar/api/keluarga/**",
            "desa-pintar/api/bantuan/**",
            "desa-pintar/api/galeri/**",
            "desa-pintar/api/pemasukan/**",
            "desa-pintar/api/penerima-bantuan/**",
            "desa-pintar/api/pengeluaran/**",
            "desa-pintar/api/saldo/**",
            "desa-pintar/api/rencana-kerja/**",

     };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(AUTH_AUTHORIZATION).hasRole("ADMIN")
                .antMatchers(AUTH_AUTHORIZATION).hasAnyRole( "ADMIN")
                .anyRequest()
                .authenticated().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}