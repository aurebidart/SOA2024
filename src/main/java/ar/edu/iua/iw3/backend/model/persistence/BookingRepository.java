package ar.edu.iua.iw3.backend.model.persistence;

import ar.edu.iua.iw3.backend.auth.User;
import ar.edu.iua.iw3.backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    public List<Booking> findAllByUser_Username (String username);

    public Optional<Booking> findById(Long id);

    public Booking save(Booking booking);
}
