package peaksoft.restapijava14.service;

import peaksoft.restapijava14.dto.SimpleResponse;
import peaksoft.restapijava14.dto.StudentRequest;
import peaksoft.restapijava14.dto.StudentResponse;
import peaksoft.restapijava14.dto.StudentResponseGet;
import peaksoft.restapijava14.entity.Student;

import java.util.List;

public interface StudentService {
    SimpleResponse saveStudent(StudentRequest studentRequest);
    StudentResponseGet getStudentById(Long id);
    List<StudentResponseGet> getAllStudents();
    void deleteStudentById(Long id);
    Student updateStudent(Long id,Student student);
    Student getStudentByEmail(String email);
}
