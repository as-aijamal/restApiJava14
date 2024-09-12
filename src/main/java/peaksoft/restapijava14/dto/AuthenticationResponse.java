package peaksoft.restapijava14.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.restapijava14.enums.Role;
@Builder
@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String email;
    private Role role;
}
