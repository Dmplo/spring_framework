package ru.plotnikov.lesson_2;

import java.time.LocalDateTime;
import java.util.Random;

public class Ticket {
    private final String number;
    private final String operation;
    private final LocalDateTime createdAt;

    public Ticket(String number, LocalDateTime createdAt) {
        this.number = number;
        this.operation = String.valueOf(Operations.values()[new Random().nextInt(Operations.values().length)]);
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Ticket{" +
               "number='" + number + '\'' +
               ", operation='" + operation + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}
