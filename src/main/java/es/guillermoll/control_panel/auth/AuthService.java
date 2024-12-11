package es.guillermoll.control_panel.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.guillermoll.control_panel.api.users.UserRepository;
import es.guillermoll.control_panel.api.users.models.Role;
import es.guillermoll.control_panel.api.users.models.User;
import es.guillermoll.control_panel.auth.jwt.JwtService;
import es.guillermoll.control_panel.auth.models.AuthResponse;
import es.guillermoll.control_panel.auth.models.LoginRequest;
import es.guillermoll.control_panel.auth.models.RegisterRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .role(Role.CLIENT)
                    .build();

            userRepository.save(user);
            return AuthResponse.builder().token(jwtService.getToken(user)).build();
        } catch (Error e) {
            return null;
        }
    }
}
