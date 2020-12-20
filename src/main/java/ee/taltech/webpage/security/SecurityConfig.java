package ee.taltech.webpage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersConfig usersConfig;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(usersConfig.getUserName()).password(passwordEncoder().encode(usersConfig.getUserPassword()))
                .authorities(Roles.USER)
                .and()
                .withUser(usersConfig.getAdminName()).password(passwordEncoder().encode(usersConfig.getAdminPassword()))
                .authorities(Roles.USER, Roles.ADMIN);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
//                     This is for url security
//                .antMatchers("/dashboard").permitAll()
//                .antMatchers("/items").permitAll()
//                .antMatchers("/comment").permitAll()
//                .antMatchers("/wishlist").permitAll()
//                .antMatchers("/cart").hasRole("USER")
                .antMatchers("/**").permitAll()
//                .antMatchers("/").permitAll()
//                    .antMatchers("/user").hasRole("USER")
//                    .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                .csrf().disable()
                .headers().httpStrictTransportSecurity().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}