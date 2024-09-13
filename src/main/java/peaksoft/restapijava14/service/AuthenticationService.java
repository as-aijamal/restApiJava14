package peaksoft.restapijava14.service;

import peaksoft.restapijava14.dto.AuthenticationResponse;
import peaksoft.restapijava14.dto.ProfileResponse;
import peaksoft.restapijava14.dto.SignInRequest;
import peaksoft.restapijava14.dto.SignUpRequest;

import java.security.Principal;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
    ProfileResponse getProfile();
    ProfileResponse getProfileWithPrincipal(Principal principal);


}
