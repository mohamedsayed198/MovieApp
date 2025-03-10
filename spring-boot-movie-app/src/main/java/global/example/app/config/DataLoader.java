package global.example.app.config;


import global.example.app.controller.auth.RegisterRequest;
import global.example.app.entity.Role;
import global.example.app.entity.UserEntity;
import global.example.app.service.AuthenticationService;
import global.example.app.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    AuthenticationService authenticationService;
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    public DataLoader (AuthenticationService authenticationService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationService = authenticationService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            UserDetails user = customUserDetailsService.loadUserByUsername("admin");
            if (user == null) {
                System.out.println("User not found, proceeding without failure.");
            }
        } catch (UsernameNotFoundException e) {
            RegisterRequest _amdin = new RegisterRequest("admin", "admin123", Role.ADMIN);
            authenticationService.register(_amdin);
        }
        try {
            UserDetails user = customUserDetailsService.loadUserByUsername("user");
            if (user == null) {
                System.out.println("User not found, proceeding without failure.");
            }
        } catch (UsernameNotFoundException e) {
            RegisterRequest _user = new RegisterRequest("user", "user123", Role.USER);
            authenticationService.register(_user);
        }
    }
}
