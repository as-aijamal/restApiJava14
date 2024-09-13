package peaksoft.restapijava14.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.restapijava14.dto.SimpleResponse;
import peaksoft.restapijava14.dto.StudentRequest;
import peaksoft.restapijava14.dto.StudentResponse;
import peaksoft.restapijava14.dto.StudentResponseGet;
import peaksoft.restapijava14.entity.Student;
import peaksoft.restapijava14.entity.User;
import peaksoft.restapijava14.enums.Role;
import peaksoft.restapijava14.repository.StudentRepository;
import peaksoft.restapijava14.repository.UserRepository;
import peaksoft.restapijava14.security.jwt.JwtService;
import peaksoft.restapijava14.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.prefs.BackingStoreException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SimpleResponse saveStudent(StudentRequest studentRequest) {
        User user= new User();
        user.setFirstName(studentRequest.getFirstName());
        user.setLastName(studentRequest.getLastName());
        user.setEmail(studentRequest.getEmail());
        user.setRole(Role.STUDENT);
        user.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
        userRepository.save(user);
        Student student = new Student();
        student.setAge(studentRequest.getAge());
        student.setCreatedDate(LocalDate.now());
        student.setBlocked(false);
        student.setUser(user);
        studentRepository.save(student);
        return new SimpleResponse(
                HttpStatus.OK,
                "Student with id " + student.getId() + " is saved!"
        );
    }

    @Override
    public StudentResponse getStudentById(Long id) {
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
    public SimpleResponse deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException(
                    String.format("Student with id %s not found", id));
        }
        studentRepository.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student with id " + id + " was deleted")
                .build();
    }


    @Override
    public SimpleResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student oldStudent = studentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(
                        String.format("Student with id %s not found", id)
                )
        );
        User user = userRepository.findById(oldStudent.getUser().getId()).orElseThrow(() ->
                new EntityNotFoundException("Not found"));
        if (user.getEmail().equals(jwtService.getAuthentication().getEmail())){
             user.setFirstName(studentRequest.getFirstName());
             user.setLastName(studentRequest.getLastName());
             user.setEmail(studentRequest.getEmail());
             userRepository.save(user);
             oldStudent.setAge(studentRequest.getAge());
             studentRepository.save(oldStudent);
         }else {
             throw new EntityNotFoundException("Forbidden");
         }
        return new SimpleResponse(
                HttpStatus.OK,
                "Student with id " + oldStudent.getId() + " is updated!"
        );
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
