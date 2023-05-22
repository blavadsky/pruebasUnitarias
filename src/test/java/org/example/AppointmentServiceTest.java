package org.example;

import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {

    @org.junit.jupiter.api.Test
    void saveAppointment() {
        Appointment appointmentEsperado = new Appointment(1,LocalDateTime.of(2023,5,30,14,30));
        AppointmentService appointmentService = new AppointmentService();

        Appointment appointmentResultado = appointmentService.saveAppointment(1,LocalDateTime.of(2023,5,30,14,30));

        Assertions.assertEquals(appointmentEsperado, appointmentResultado);
    }

    @org.junit.jupiter.api.Test
    void getAppointment() {
        Appointment appointmentEsperado = new Appointment(1,LocalDateTime.of(2023,5,30,14,30));
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.saveAppointment(appointmentEsperado);

        Appointment appointmentResultado = appointmentService.getAppointment(1);

        Assertions.assertEquals(appointmentEsperado, appointmentResultado);
    }

    @org.junit.jupiter.api.Test
    void updateAppointment() {
        Appointment appointment = new Appointment(1,LocalDateTime.of(2023,5,30,14,30));
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.saveAppointment(appointment);

        Appointment appointmentEsperado = new Appointment(1, LocalDateTime.of(2023,5,30,14,40));
        appointmentService.updateAppointment(appointmentEsperado);

        Assertions.assertEquals(appointmentEsperado, appointmentService.getAppointment(1));

    }

    @org.junit.jupiter.api.Test
    void delete() {
        Appointment appointment = new Appointment(1,LocalDateTime.of(2023,5,30,14,30));
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.saveAppointment(appointment);

        boolean eliminado = appointmentService.delete(appointment);

        Assertions.assertNotEquals(false, eliminado);

    }

}