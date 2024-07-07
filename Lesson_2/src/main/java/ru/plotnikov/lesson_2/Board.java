package ru.plotnikov.lesson_2;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class Board {

    TicketNumberGenerator ticketNumberGenerator;

    public Board(TicketNumberGenerator ticketNumberGenerator) {
        this.ticketNumberGenerator = ticketNumberGenerator;
    }

    public Ticket newTicket() {
        return new Ticket(ticketNumberGenerator.createNewNumber(), LocalDateTime.now());
    }
}
