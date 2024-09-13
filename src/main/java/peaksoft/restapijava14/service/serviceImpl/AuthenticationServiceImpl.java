package peaksoft.restapijava14.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.restapijava14.dto.AuthenticationResponse;
import peaksoft.restapijava14.dto.ProfileResponse;
import peaksoft.restapijava14.dto.SignInRequest;
import peaksoft.restapijava14.dto.SignUpRequest;
import peaksoft.restapijava14.entity.User;
import peaksoft.restapijava14.enums.Role;
import peaksoft.restapijava14.repository.UserRepository;
import peaksoft.restapijava14.security.jwt.JwtService;
import peaksoft.restapijava14.service.AuthenticationService;

import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @PostConstruct
    public void saveAdmin(){
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Adminov");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("Admin123"));
        user.setRole(Role.ADMIN);
        if (!userRepository.existsByEmail(user.getEmail())){
            userRepository.save(user);
        }
    }


    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EntityExistsException(String.format(
                    "User with email %s already exists"
                    , signUpRequest.getEmail()));
        }
        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new EntityNotFoundException(String.format(
                        "User with email %s not found", signInRequest.getEmail()
                ))
        );
        if (signInRequest.getEmail().isBlank()) {
            throw new BadCredentialsException("Invalid email!");
        }
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public ProfileResponse getProfile() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("Not found"));
        return ProfileResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public ProfileResponse getProfileWithPrincipal(Principal principal) {
        String email = getProfile().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("Not found"));
        return ProfileResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}



















