package peaksoft.restapijava14.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String password;

}
