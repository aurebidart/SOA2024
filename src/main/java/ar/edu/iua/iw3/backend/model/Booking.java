package ar.edu.iua.iw3.backend.model;

import ar.edu.iua.iw3.backend.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date checkin;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer rooms;

    @Column(nullable = false)
    private Integer peopleAmount;

    @Column(nullable = false)
    private Double price;

    @OneToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
}
