package com.tarun.TodoApp.TodoApplication.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;


@Configuration
public class TodoSpringSecurity {
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager(){
		
		UserDetails user1 = createNewUser( "Tarun", "Narayana");
		UserDetails user2 = createNewUser( "Ramapuram", "Narayana123");
		return new InMemoryUserDetailsManager(user1,user2);
	
	}

	private UserDetails createNewUser( String username,String password) {
		Function<String, String> encodePasswordFunction = input ->
		passwordEncoder().encode(input);
		UserDetails user = User.builder()
							.passwordEncoder(encodePasswordFunction )
							.username(username)
							.password(password)
							.roles("USER","ADMIN")
							.build();
		return user;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
}
