package peaksoft.restapijava14.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class StudentResponse {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private LocalDate createdDate;

    public StudentResponse(String firstName, String lastName, String email, int age, LocalDate createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.createdDate = createdDate;
    }
}
