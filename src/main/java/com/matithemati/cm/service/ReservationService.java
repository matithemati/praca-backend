package com.matithemati.cm.service;

import com.matithemati.cm.model.CosmeticService;
import com.matithemati.cm.model.Employee;
import com.matithemati.cm.model.Reservation;
import com.matithemati.cm.repository.CosmeticServiceRepository;
import com.matithemati.cm.repository.CustomerRepository;
import com.matithemati.cm.repository.EmployeeRepository;
import com.matithemati.cm.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final CosmeticServiceRepository cosmeticServiceRepository;

    public Reservation addReservation(Reservation reservation) {
        if (reservation.getReservationDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Reservation date must be in the future");
        }
        if (customerRepository.findById(reservation.getCustomerId()).isEmpty()) {
            throw new IllegalStateException("Employee does not exist");
        }
        if (employeeRepository.findById(reservation.getEmployeeId()).isEmpty()) {
            throw new IllegalStateException("Employee does not exist");
        }
        if (cosmeticServiceRepository.findById(reservation.getCosmeticServiceId()).isEmpty()) {
            throw new IllegalStateException("Cosmetic service does not exist");
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByCustomerId(Long customerId) {
        return reservationRepository.findAllByCustomerId(customerId);
    }

    public List<Reservation> getFutureReservations(Long customerId) {
        return reservationRepository.findReservationsByCustomerIdAndReservationDateTimeAfterAndStatus(customerId, LocalDateTime.now(), "NEW");
    }

    public List<Reservation> getPastReservations(Long customerId) {
        return reservationRepository.findAllByCustomerIdAndStatus(customerId, "COMPLETED");
    }

    public List<Employee> getEmployeesList() {
        return employeeRepository.findAll();
    }

    public List<CosmeticService> getCosmeticServicesList() {
        return cosmeticServiceRepository.findAll();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll(Sort.by(Sort.Direction.DESC, "reservationDateTime"));
    }

    public Reservation setReservationStatus(Long reservationId, String status) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new IllegalStateException("Reservation does not exist"));
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }

    public Reservation rateReservation(Long reservationId, int rating) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new IllegalStateException("Reservation does not exist"));
        reservation.setRating(rating);
        return reservationRepository.save(reservation);
    }
}