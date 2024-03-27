package com.spingboot.hashid.controller;

import com.spingboot.hashid.entity.Student;
import com.spingboot.hashid.models.StudentCourseDetailsDTO;
import com.spingboot.hashid.request.StudentRequestDto;
import com.spingboot.hashid.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student-course-details/{id}")
    public ResponseEntity<StudentCourseDetailsDTO> getStudentCourseDetails(@PathVariable Long id) {
        StudentCourseDetailsDTO studentCourseDetailsList = studentService.getStudentCourseDetails(id);
        return ResponseEntity.ok(studentCourseDetailsList);
    }

    @GetMapping("/student-course-details-by-function/{id}")
    public ResponseEntity<StudentCourseDetailsDTO> getStudentCourseDetailsByFunction(@PathVariable Long id) {
        StudentCourseDetailsDTO studentCourseDetailsList = studentService.getStudentCourseDetailsByFunction(id);
        return ResponseEntity.ok(studentCourseDetailsList);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentCourseDetailsList = studentService.getAllStudents();
        return ResponseEntity.ok(studentCourseDetailsList);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent(StudentRequestDto studentRequestDto){
        studentService.saveStudent(studentRequestDto);
        return ResponseEntity.ok("Student Saved Successfully!");
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<String> updateStudent(StudentRequestDto studentRequestDto,@PathVariable Long id){
        studentService.updateStudent(studentRequestDto,id);
        return ResponseEntity.ok("Student Updated Successfully!");
    }

    @DeleteMapping("/removeStudent/{id}")
    public ResponseEntity<String> removeStudent(@PathVariable Long id){
        studentService.removeStudent(id);
        return ResponseEntity.ok("Student Deleted Successfully!");
    }
}
