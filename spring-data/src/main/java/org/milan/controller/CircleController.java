package org.milan.controller;

import org.milan.model.Circle;
import org.milan.service.CircleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Circle
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/circle")
public class CircleController {

    private CircleService circleService;

    public CircleController(CircleService circleService) {
        this.circleService = circleService;
    }

    @PostMapping("/create")
    public void create(@RequestBody Circle circle) {
        circleService.create(circle);
    }

    @GetMapping("/get/{id}")
    public Circle get(@PathVariable int id) {
        return circleService.get(id);
    }

    @GetMapping("/get/all")
    public List<Circle> getAll() {
        return circleService.getAll();
    }

    @GetMapping("/get/count")
    public int getCount() {
        return circleService.getCount();
    }

}
