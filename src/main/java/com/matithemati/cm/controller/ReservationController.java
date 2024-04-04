package com.matithemati.cm.controller;

import com.matithemati.cm.model.CosmeticService;
import com.matithemati.cm.model.Employee;
import com.matithemati.cm.model.Reservation;
import com.matithemati.cm.repository.CosmeticServiceRepository;
import com.matithemati.cm.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final CosmeticServiceRepository cosmeticServiceRepository;

    @GetMapping("/findFutureReservations/{customerId}")
    public ResponseEntity<List<Reservation>> getFutureReservations(@PathVariable Long customerId) {

        List<Reservation> futureReservations = reservationService.getFutureReservations(customerId);

        return ResponseEntity.ok(futureReservations);
    }

    @GetMapping("/findPastReservations/{customerId}")
    public ResponseEntity<List<Reservation>> getPastReservations(@PathVariable Long customerId) {

        List<Reservation> pastReservations = reservationService.getPastReservations(customerId);


        return ResponseEntity.ok(pastReservations);
    }
    @GetMapping("/findReservationsByCustomerId/{customerId}")
    public ResponseEntity<List<Reservation>> getReservationsByCustomerId(@PathVariable Long customerId) {

        List<Reservation> reservations = reservationService.getReservationsByCustomerId(customerId);

        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/getEmployeesList")
    public ResponseEntity<List<Employee>> getEmployeesList() {
        return ResponseEntity.ok(reservationService.getEmployeesList());
    }

    @GetMapping("/getCosmeticServices")
    public ResponseEntity<List<CosmeticService>> getCosmeticServices() {
        return ResponseEntity.ok(reservationService.getCosmeticServicesList());
    }

    @PostMapping("/addCosmeticService")
    public ResponseEntity<CosmeticService> addCosmeticService(@RequestBody CosmeticService cosmeticService) {
        return ResponseEntity.ok(cosmeticServiceRepository.save(cosmeticService));
    }

    @PostMapping("/addReservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }

    @GetMapping("/getAllReservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PatchMapping("/setReservationStatus/{reservationId}/{status}")
    public ResponseEntity<Reservation> setReservationStatus(@PathVariable Long reservationId, @PathVariable String status) {
        return ResponseEntity.ok(reservationService.setReservationStatus(reservationId, status));
    }

    @PatchMapping("/rateReservation/{reservationId}/{rating}")
    public ResponseEntity<Reservation> rateReservation(@PathVariable Long reservationId, @PathVariable int rating) {
        return ResponseEntity.ok(reservationService.rateReservation(reservationId, rating));
    }


}
