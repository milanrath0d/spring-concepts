package org.milan.service;

import org.milan.model.Hotel;
import org.milan.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Hotel service class
 *
 * @author Milan Rathod
 */
@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public void insert(Hotel hotel) {
        hotelRepository.insert(hotel);
    }

    public void update(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void delete(String id) {
        hotelRepository.deleteById(id);
    }

    public Hotel getById(String id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public List<Hotel> getByPriceByNight(int maxPrice) {
        return hotelRepository.findByPricePerNightLessThan(maxPrice);
    }

    public List<Hotel> getByCity(String city) {
        return hotelRepository.findByCity(city);
    }
}
