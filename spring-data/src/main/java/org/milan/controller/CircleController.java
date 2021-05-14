package org.milan.controller;

import org.milan.model.Circle;
import org.milan.service.CircleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Circle
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/circle")
public class CircleController {

    private final CircleService circleService;

    public CircleController(CircleService circleService) {
        this.circleService = circleService;
    }

    @PostMapping
    public void create(@RequestBody Circle circle) {
        circleService.create(circle);
    }

    @GetMapping("/{id}")
    public Circle get(@PathVariable int id) {
        return circleService.get(id);
    }

    @GetMapping
    public List<Circle> getAll() {
        return circleService.getAll();
    }

    @GetMapping("/count")
    public int getCount() {
        return circleService.getCount();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (circleService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
