package peaksoft.restapijava14.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator = "group_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_gen",
            sequenceName = "group_seq",allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;

    @OneToMany(mappedBy = "group", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<Student> students;

    public Group(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
}
