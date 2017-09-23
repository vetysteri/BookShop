package wad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
              System.out.println("MOIIII");
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/happypath").permitAll()
        .antMatchers(HttpMethod.GET, "/secretpath").hasAnyAuthority("ADMIN","USER")
        .antMatchers(HttpMethod.GET, "/adminpath").hasAnyAuthority("ADMIN")
        .antMatchers(HttpMethod.GET, "/books").permitAll()
        .antMatchers(HttpMethod.GET, "/cart").permitAll()
        .antMatchers(HttpMethod.POST, "/books").permitAll()   
        .antMatchers(HttpMethod.POST, "/cart").permitAll()  
        .anyRequest().authenticated();
        http.formLogin().permitAll().and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
