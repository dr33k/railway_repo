package com.seven.RailroadApp.controllers;

import com.seven.RailroadApp.config.security.UserAuthentication;
import com.seven.RailroadApp.models.entities.User;
import com.seven.RailroadApp.models.records.BookingRecord;
import com.seven.RailroadApp.models.records.TicketRecord;
import com.seven.RailroadApp.models.records.TicketRecord;
import com.seven.RailroadApp.models.responses.Response;
import com.seven.RailroadApp.services.BookingService;
import com.seven.RailroadApp.services.TicketService;
import com.seven.RailroadApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    BookingService bookingService;
    @Autowired
    UserAuthentication userAuthentication;
    
    @GetMapping("/search")
    public ResponseEntity<Response> getResource(@RequestParam(name = "booking_no") UUID bookingNo){
        User sender = (User) userAuthentication.getInstance().getPrincipal();
        BookingRecord bookingRecord = (BookingRecord) bookingService.get(bookingNo);

        if (bookingRecord == null) {       //If resource was not found
            return ResponseEntity.status(404).body(Response.builder()
                    .isError(true)
                    .message("Booking " + bookingRecord.bookingNo() + " is not available")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(LocalDateTime.now())
                    .build());
        } else if (!(bookingRecord.message() == null)) {   //If there was an error during the process
            return ResponseEntity.status(404).body(Response.builder()
                    .isError(true)
                    .message(bookingRecord.message())
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(LocalDateTime.now())
                    .build());
        }
        else if (!sender.getEmail().equals(bookingRecord.passengerEmail())) {//If user does not own booking
            return ResponseEntity.ok(Response.builder()
                    .isError(false)
                    .message("You have no reservations buy this booking number")
                    .status(HttpStatus.OK)
                    .timestamp(LocalDateTime.now())
                    .build());
        }else {
            //Search for ticket
            TicketRecord tr = (TicketRecord) ticketService.getByBookingNo(bookingNo);
            if (tr == null) {       //If resource was not found
                return ResponseEntity.status(404).body(Response.builder()
                        .isError(true)
                        .message("Ticket with booking number " + bookingNo + " is not available")
                        .status(HttpStatus.NOT_FOUND)
                        .timestamp(LocalDateTime.now())
                        .build());
            } else {
                return ResponseEntity.ok(Response.builder()
                        .data(Set.of(tr))
                        .isError(false)
                        .status(HttpStatus.FOUND)
                        .timestamp(LocalDateTime.now())
                        .build());
            }
        }
    }

}
