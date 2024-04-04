package com.matithemati.cm.repository;

import com.matithemati.cm.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationsByCustomerIdAndReservationDateTimeAfterAndStatus(Long customerId, LocalDateTime reservationDateTime, String status);

    List<Reservation> findReservationsByCustomerIdAndReservationDateTimeBefore(Long customerId, LocalDateTime reservationDateTime);

    boolean existsByEmployeeId(Long id);

    List<Reservation> findAllByCustomerId(Long customerId);

    List<Reservation> findAllByCustomerIdAndStatus(Long customerId, String status);
}
