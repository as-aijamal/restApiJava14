package peaksoft.restapijava14.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_gen"
    )
    @SequenceGenerator(
            name = "student_gen",
            sequenceName = "student_seq",
            allocationSize = 1
    )
    private Long id;
    private int age;
    private LocalDate createdDate;
    private boolean isBlocked;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Group group;
    @OneToOne
    private User user;
}
