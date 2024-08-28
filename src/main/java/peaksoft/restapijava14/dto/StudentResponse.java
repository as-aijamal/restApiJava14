package peaksoft.restapijava14.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class StudentResponse {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private LocalDate createdDate;


}
