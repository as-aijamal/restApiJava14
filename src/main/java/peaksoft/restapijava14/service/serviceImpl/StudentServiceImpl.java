package peaksoft.restapijava14.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restapijava14.dto.SimpleResponse;
import peaksoft.restapijava14.dto.StudentRequest;
import peaksoft.restapijava14.dto.StudentResponse;
import peaksoft.restapijava14.dto.StudentResponseGet;
import peaksoft.restapijava14.entity.Student;
import peaksoft.restapijava14.repository.StudentRepository;
import peaksoft.restapijava14.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public SimpleResponse saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());
        student.setCreatedDate(LocalDate.now());
        student.setBlocked(false);
        studentRepository.save(student);
        return new SimpleResponse(
                HttpStatus.OK,
                "Student with id "+student.getId()+" is saved!"
        );
//        return new StudentResponse(
//                student.getFirstName(),
//                student.getLastName(),
//                student.getEmail(),
//                student.getAge(),
//                student.getCreatedDate()
//        );
    }

    @Override
    public StudentResponseGet getStudentById(Long id) {
        return studentRepository.getStudentById(id).orElseThrow(
                () -> new NoSuchElementException(
                        String.format("Student with id %s not found", id)
                )
        );
    }

    @Override
    public List<StudentResponseGet> getAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException(
                    String.format("Student with id %s not found", id));
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
//        Student oldStudent = getStudentById(id);
//        oldStudent.setFirstName(student.getFirstName());
//        oldStudent.setLastName(student.getLastName());
//        oldStudent.setEmail(student.getEmail());
//        oldStudent.setAge(student.getAge());
//        studentRepository.save(oldStudent);
        return null;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
