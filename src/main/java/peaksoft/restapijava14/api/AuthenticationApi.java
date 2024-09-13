package peaksoft.restapijava14.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restapijava14.dto.AuthenticationResponse;
import peaksoft.restapijava14.dto.ProfileResponse;
import peaksoft.restapijava14.dto.SignInRequest;
import peaksoft.restapijava14.dto.SignUpRequest;
import peaksoft.restapijava14.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    AuthenticationResponse signIn(@RequestBody SignInRequest signInRequest){
        return authenticationService.signIn(signInRequest);
    }

    @GetMapping
    @PermitAll
    ProfileResponse getProfile(){
        return authenticationService.getProfile();
    }

}
