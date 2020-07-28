package org.milan.controller;

import org.milan.model.Hotel;
import org.milan.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for {@link Hotel}
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all")
    public List<Hotel> getAll() {
        Logger logger = LoggerFactory.getLogger(HotelController.class);
        logger.trace("test");
        return hotelService.getAll();
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable String id) {
        return hotelService.getById(id);
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPriceByNight(@PathVariable int maxPrice) {
        return hotelService.getByPriceByNight(maxPrice);
    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable String city) {
        return hotelService.getByCity(city);
    }

    @PostMapping
    public void insert(@RequestBody Hotel hotel) {
        hotelService.insert(hotel);
    }

    @PutMapping
    public void update(@RequestBody Hotel hotel) {
        hotelService.update(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        hotelService.delete(id);
    }
}
