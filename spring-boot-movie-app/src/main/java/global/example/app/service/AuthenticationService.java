package global.example.app.service;


import global.example.app.controller.auth.AuthenticationRequest;
import global.example.app.controller.auth.AuthenticationResponse;
import global.example.app.controller.auth.RegisterRequest;
import global.example.app.entity.UserEntity;
import global.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthenticationService {

    private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    @Autowired
    public AuthenticationService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .userName(request.getUserName())
                .userPassword(passwordEncoder.encode(request.getPassword()))
                .userRole(request.getRole())
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //if username or password incorrect exception will be thrown
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        var user = userRepo.findByUserName(request.getUserName()).orElseThrow();
        logger.info(user.getUserRole().name());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
