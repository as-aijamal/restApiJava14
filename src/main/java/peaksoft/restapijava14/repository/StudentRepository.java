package peaksoft.restapijava14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.restapijava14.dto.StudentResponse;
import peaksoft.restapijava14.dto.StudentResponseGet;
import peaksoft.restapijava14.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s join s.user u where u.email=:email")
    Student findByEmail(String email);

    @Query("select new peaksoft.restapijava14.dto.StudentResponseGet " +
            "(concat(u.firstName,' ',u.lastName) ,u.email,s.age,s.createdDate) from Student s join s.user u ")
    List<StudentResponseGet> findAllStudents();

    @Query("select new peaksoft.restapijava14.dto.StudentResponse" +
            "(u.firstName,u.lastName,u.email,s.age,s.createdDate) from Student s join s.user u where s.id=:id")
    Optional<StudentResponse> getStudentById(Long id);

    Optional<Student> findById(Long id);


}
