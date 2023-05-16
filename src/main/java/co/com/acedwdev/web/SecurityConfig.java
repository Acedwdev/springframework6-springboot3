package co.com.acedwdev.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    public void configure(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()      
//                .anyRequest().permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/edit/**", "/add/**", "/delete", "/save").hasRole("ADMIN")
                .requestMatchers("/", "/errors/403").hasAnyRole("USER", "ADMIN")
                
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()                
                
                .and()                
//                .exceptionHandling().accessDeniedPage("/errors/403")
//                .and()
//                .csrf().disable()
                .build();

    }
        
}
