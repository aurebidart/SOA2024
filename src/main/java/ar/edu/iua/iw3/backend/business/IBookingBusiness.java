package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.auth.User;
import ar.edu.iua.iw3.backend.business.Exceptions.BusinessException;
import ar.edu.iua.iw3.backend.model.Booking;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IBookingBusiness {
    public Booking save(Booking booking) throws BusinessException;
    public Booking load(Long id) throws BusinessException;
    public List<Booking> findByUser(String username) throws BusinessException;

}
