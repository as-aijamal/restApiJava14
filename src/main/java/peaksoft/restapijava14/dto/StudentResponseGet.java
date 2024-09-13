package peaksoft.restapijava14.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder

public class StudentResponseGet {
    private String fullName;
    private String email;
    private int age;
    private LocalDate createdDate;

    public StudentResponseGet(String fullName, String email, int age, LocalDate createdDate) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.createdDate = createdDate;
    }
}
