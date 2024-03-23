package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.Exceptions.BusinessException;
import ar.edu.iua.iw3.backend.business.Exceptions.NotFoundException;
import ar.edu.iua.iw3.backend.model.Hotel;
import ar.edu.iua.iw3.backend.model.persistence.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@EnableAsync
@Service
public class HotelBusiness implements IHotelBusiness{

    @Autowired
    private HotelRepository hotelDAO;

    @Override
    public List<Hotel> list() throws BusinessException {
        try {
            return hotelDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Override
    public Hotel load(Long id) throws BusinessException {
        try {
            return hotelDAO.findById(id).orElseThrow(() -> new NotFoundException("No se encuentra el hotel con id " + id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Hotel> loadByName(String name) throws BusinessException {
        try {
            return hotelDAO.findByName(name);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Hotel> loadByCity(String city) throws BusinessException {
        try {
            return hotelDAO.findByCity(city);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }
}
