package org.milan.controller;

import org.milan.model.Student;
import org.milan.util.DummyStudents;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Rest Controller for Student
 *
 * @author Milan Rathod
 */
@RestController
public class StudentRestController {

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents() {
        return DummyStudents.allStudents();
    }

    @GetMapping("/students/{name}")
    public Student getStudent(@PathVariable String name) {
        List<Student> students = DummyStudents.allStudents();

        Optional<Student> result = students.stream().filter(student -> student.getName().equals(name))
                .findFirst();

        return result.get();
    }

    @PutMapping(value = "/students/{name}")
    public ResponseEntity<Boolean> updateStudent(@PathVariable String name, @RequestBody Student student) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("test", "test-value");

        return new ResponseEntity<>(true, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/students")
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location",
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{name}")
                        .buildAndExpand(student.getName()).toUriString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/students/{name}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String name) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
