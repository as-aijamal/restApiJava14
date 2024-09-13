package peaksoft.restapijava14.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.restapijava14.enums.Role;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
