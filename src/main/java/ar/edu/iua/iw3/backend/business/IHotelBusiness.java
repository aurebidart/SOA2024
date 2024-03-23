package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.Exceptions.BusinessException;
import ar.edu.iua.iw3.backend.model.Hotel;

import java.util.List;


public interface IHotelBusiness {
    public List<Hotel> list() throws BusinessException;

    public Hotel load(Long id) throws BusinessException;

    public List<Hotel> loadByName(String name) throws BusinessException;

    public List<Hotel> loadByCity(String city) throws BusinessException;

}
