package ar.edu.iua.iw3.backend.util.controllers;

import ar.edu.iua.iw3.backend.business.Exceptions.BusinessException;
import ar.edu.iua.iw3.backend.business.IBookingBusiness;
import ar.edu.iua.iw3.backend.model.Booking;
import ar.edu.iua.iw3.backend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.URL_BOOKINGS)
public class BookingRestController extends BaseRestController {

    @Autowired
    private IBookingBusiness bookingBusiness;

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByUser() {
        try {
            return new ResponseEntity<>(bookingBusiness.findByUser(getUserLogged().getUsername()), HttpStatus.OK);
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Booking booking) {
        try {
            booking.setUser(getUserLogged());
            return new ResponseEntity<>(bookingBusiness.save(booking), HttpStatus.OK);
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
