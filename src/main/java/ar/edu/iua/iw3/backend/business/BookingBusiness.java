package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.Exceptions.BusinessException;
import ar.edu.iua.iw3.backend.model.Booking;
import ar.edu.iua.iw3.backend.model.persistence.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@EnableAsync
@Service
public class BookingBusiness implements IBookingBusiness{

    @Autowired
    private BookingRepository bookingDAO;

    @Override
    public Booking save(Booking booking) throws BusinessException {
        try {
            return bookingDAO.save(booking);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(e);
        }
    }

    @Override
    public Booking load(Long id) throws BusinessException {
        try {
            return bookingDAO.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Booking> findByUser(String username) throws BusinessException {
        try {
            return bookingDAO.findAllByUser_Username(username);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(e);
        }
    }
}
