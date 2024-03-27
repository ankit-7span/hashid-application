package com.spingboot.hashid.controller;

import com.spingboot.hashid.entity.Student;
import com.spingboot.hashid.repository.StudentRepository;
import com.spingboot.hashid.request.StudentRequest;
import com.spingboot.hashid.request.StudentResponse;
import com.spingboot.hashid.service.HashidsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HashidsController {

    private final HashidsService hashidsService;

    private final StudentRepository studentRepository;



    public HashidsController(HashidsService hashidsService, StudentRepository studentRepository, StudentRepository studentRepository1) {
        this.hashidsService = hashidsService;
        this.studentRepository = studentRepository1;
    }

    @PostMapping("/encode/{number}")
    public String encode(@PathVariable Long number) {
        return hashidsService.encode(number);
    }

    @GetMapping("/decode/{hash}")
    public long decode(@PathVariable String hash) {
        return hashidsService.decode(hash);
    }

/*    @PostMapping("/encode")
    public StudentResponse encode(@RequestBody StudentRequest studentRequest) {
        //Student save = studentRepository.save(new Student(studentRequest.id(), "name"));
        return new StudentResponse(save.getId(),save.getName());
    }*/
}
