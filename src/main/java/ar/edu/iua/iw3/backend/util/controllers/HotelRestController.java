package ar.edu.iua.iw3.backend.util.controllers;


import ar.edu.iua.iw3.backend.business.IHotelBusiness;
import ar.edu.iua.iw3.backend.model.Hotel;
import ar.edu.iua.iw3.backend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_HOTELS)
public class HotelRestController {

    @Autowired
    private IHotelBusiness hotelBusiness;

    @GetMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        try {
            return ResponseEntity.ok(hotelBusiness.list());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> load(@PathVariable long id) {
        try {
            return ResponseEntity.ok(hotelBusiness.load(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(hotelBusiness.loadByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/city/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadByCity(@PathVariable String city) {
        try {
            return ResponseEntity.ok(hotelBusiness.loadByCity(city));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
