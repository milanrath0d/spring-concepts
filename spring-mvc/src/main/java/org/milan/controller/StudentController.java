package org.milan.controller;

import org.milan.model.Student;
import org.milan.util.DummyStudents;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Rest Controller for Student
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudents() {
        return DummyStudents.allStudents();
    }

    @GetMapping("/{name}")
    public Student getStudent(@PathVariable String name) {
        List<Student> students = DummyStudents.allStudents();

        Optional<Student> result = students.stream().filter(student -> student.getName().equals(name))
            .findFirst();

        return result.orElse(null);
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity<Boolean> updateStudent(@PathVariable String name, @RequestBody Student student) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("test", "test-value");

        return new ResponseEntity<>(true, httpHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location",
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(student.getName()).toUriString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String name) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
