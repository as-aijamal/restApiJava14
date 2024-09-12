package peaksoft.restapijava14.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restapijava14.dto.SimpleResponse;
import peaksoft.restapijava14.dto.StudentRequest;
import peaksoft.restapijava14.dto.StudentResponse;
import peaksoft.restapijava14.dto.StudentResponseGet;
import peaksoft.restapijava14.entity.Student;
import peaksoft.restapijava14.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse saveStudent(@RequestBody StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @PermitAll
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }


    @GetMapping
        @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    public List<StudentResponseGet> getAllStudents(){
        return studentService.getAllStudents();
    }

    @DeleteMapping()
   @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteStudentById(@RequestParam Long id){
        return studentService.deleteStudentById(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateStudent(@PathVariable Long id, @RequestBody Student student){
        return studentService.updateStudent(id,student);
    }

    @GetMapping("/getByEmail")
    public Student getByEmail(@RequestParam String email){
        return studentService.getStudentByEmail(email);
    }
















}
