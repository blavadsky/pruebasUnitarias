package org.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Appointment {
    private LocalDateTime availableDate;
    private int id;
    private static HashMap<Integer, Appointment> appointmentMap = new HashMap<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id &&
                Objects.equals(availableDate, that.availableDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, availableDate);
    }
    public LocalDateTime getAvailableDate() { return availableDate; }

    public void setAvailableDate(LocalDateTime availableDate) { this.availableDate = availableDate; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment(int id,LocalDateTime availableDate) {
        this.availableDate = availableDate;
        this.id = id;
    }


}
