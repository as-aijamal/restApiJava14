package peaksoft.restapijava14.api;

import lombok.RequiredArgsConstructor;
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
    public SimpleResponse saveStudent(@RequestBody StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<StudentResponseGet> getAllStudents(){
        return studentService.getAllStudents();
    }

    @DeleteMapping()
    public SimpleResponse deleteStudentById(@RequestParam Long id){
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse updateStudent(@PathVariable Long id, @RequestBody Student student){
        return studentService.updateStudent(id,student);
    }

    @GetMapping("/getByEmail")
    public Student getByEmail(@RequestParam String email){
        return studentService.getStudentByEmail(email);
    }
















}
