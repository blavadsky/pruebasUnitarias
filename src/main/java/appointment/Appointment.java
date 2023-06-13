package appointment;

import users.Doctor.Doctor;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {
    private LocalDateTime availableDate;
    private LocalDateTime dataTime;
    private int id;
    private Doctor doctor;
    private boolean paid;
    private String prescription;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }


    public boolean isPaid() { return paid; }

    public void setPaid(boolean paid) { this.paid = paid; }

    public String getPrescription() { return prescription; }

    public void setPrescription(String prescription) { this.prescription = prescription; }

    public Appointment(LocalDateTime availableDate) {
        this.availableDate = availableDate;
        this.id = id;
    }

}
