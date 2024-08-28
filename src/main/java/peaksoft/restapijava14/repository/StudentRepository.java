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

    Student findByEmail(String email);

    @Query("select new peaksoft.restapijava14.dto.StudentResponseGet " +
            "(concat(s.firstName,' ',s.lastName) ,s.email,s.age,s.createdDate) from Student s")
    List<StudentResponseGet>findAllStudents();

    @Query("select new peaksoft.restapijava14.dto.StudentResponseGet " +
            "(concat(s.firstName,' ',s.lastName),s.email,s.age,s.createdDate) from Student s where s.id=:id")
    Optional<StudentResponseGet> getStudentById(Long id);



}
