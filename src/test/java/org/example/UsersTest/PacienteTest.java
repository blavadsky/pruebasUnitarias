package org.example.UsersTest;

import appointment.Appointment;
import appointment.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import users.Paciente;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class PacienteTest {

    @Mock
    private AppointmentService appointmentService;
    private Appointment appointment;
    private Paciente paciente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        paciente = new Paciente("password", "email", appointmentService);
        appointment = new Appointment(LocalDateTime.of(2023,5,30,14,30));
    }

    @Test
    void appointmentCancelation_ExistingAppointment() {
        when(appointmentService.getAppointmentByDateTime(appointment.getDataTime())).thenReturn(appointment);

        Appointment result = paciente.appointmentCancelation(appointment);

        Assertions.assertEquals(appointment, result);
        verify(appointmentService, times(1)).deleteAppointment(appointment);
    }

    @Test
    void appointmentCancelation_NonExistingAppointment() {
        when(appointmentService.getAppointmentByDateTime(appointment.getDataTime())).thenReturn(null);

        Appointment result = paciente.appointmentCancelation(appointment);

        Assertions.assertNull(result);
        verify(appointmentService, never()).deleteAppointment(appointment);
    }

    @Test
    void appointmentPay_PaidAppointment_ReturnsTrue() {
        appointment.setPaid(true);

        boolean result = paciente.appointmentPay(appointment);

        Assertions.assertTrue(result);
    }

    @Test
    void appointmentPay_UnpaidAppointment_ReturnsFalse() {
        appointment.setPaid(false);

        boolean result = paciente.appointmentPay(appointment);

        Assertions.assertFalse(result);
    }

}