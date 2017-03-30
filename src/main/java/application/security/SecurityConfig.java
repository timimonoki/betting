package application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    public SecurityConfig() {
        super();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").authenticated()
                    .antMatchers(HttpMethod.GET,"/getCustomers").hasAnyRole("USER","ADMIN")
                    .antMatchers(HttpMethod.GET,"/getBets").hasAnyRole("USER","ADMIN")
                    .antMatchers(HttpMethod.GET,"/getEvents").hasAnyRole("USER","ADMIN")
                    .antMatchers(HttpMethod.GET,"/getCustomer").hasAnyRole("USER","ADMIN")
                    .antMatchers(HttpMethod.GET,"/getBet").hasAnyRole("USER","ADMIN")
                    .antMatchers(HttpMethod.GET,"/getEvent").hasAnyRole("USER","ADMIN")
                    .anyRequest().hasRole("ADMIN")
                .and()
                .httpBasic();

        http.csrf().disable();
    }
}
