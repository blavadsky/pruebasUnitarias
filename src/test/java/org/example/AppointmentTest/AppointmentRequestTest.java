package org.example.AppointmentTest;

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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentRequestTest {

    @Mock
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void appointmentRequest_ExistingAppointment() {
        Paciente paciente = new Paciente("password", "paciente@gmail.com", appointmentService);

        LocalDateTime appointmentDateTime = LocalDateTime.of(2023, 5, 30, 14, 30);
        Appointment existingAppointment = new Appointment(appointmentDateTime);

        when(appointmentService.getAppointmentByDateTime(appointmentDateTime))
                .thenReturn(existingAppointment);

        Appointment result = paciente.appointmentRequest(appointmentDateTime);

        verify(appointmentService, times(1)).getAppointmentByDateTime(appointmentDateTime);
        verify(appointmentService, never()).saveAppointment(anyInt(), any(Appointment.class));
        Assertions.assertEquals(existingAppointment, result);
    }



    @Test
    void appointmentRequest_NewAppointment() {

        Paciente paciente = new Paciente("password", "paciente@gmail.com", appointmentService);
        LocalDateTime appointmentDateTime = LocalDateTime.of(2023, 5, 30, 14, 30);
        Appointment newAppointment = new Appointment(appointmentDateTime);

        when(appointmentService.getAppointmentByDateTime(appointmentDateTime)).thenReturn(null);
        when(appointmentService.saveAppointment(anyInt(), any(LocalDateTime.class))).thenReturn(newAppointment);


        Appointment result = paciente.appointmentRequest(appointmentDateTime);

        verify(appointmentService, times(1)).getAppointmentByDateTime(appointmentDateTime);
        verify(appointmentService, times(1)).saveAppointment(anyInt(), eq(appointmentDateTime));
        Assertions.assertEquals(newAppointment, result);
    }


    @Test
    public void testAppointmentCancelation_ExistingAppointment() {
        LocalDateTime appointmentDateTime = LocalDateTime.of(2023, 6, 1, 10, 0);
        Appointment existingAppointment = appointmentService.saveAppointment(1, appointmentDateTime);

        lenient().when(appointmentService.saveAppointment(anyInt(), any(LocalDateTime.class)))
                .thenReturn(existingAppointment);

        Paciente paciente = new Paciente("password", "paciente@gmail.com", appointmentService);;
        Appointment canceledAppointment = paciente.appointmentCancelation(new Appointment(LocalDateTime.of(2023, 6, 1, 10, 0)));

        Assertions.assertNull(appointmentService.getAppointment(1));
        Assertions.assertEquals(existingAppointment, canceledAppointment);
    }

    @Test
    public void testAppointmentCancelation_NonExistingAppointment() {

        lenient().when(appointmentService.deleteAppointment(any(Appointment.class))).thenReturn(false);

        Paciente paciente = new Paciente("password", "paciente@gmail.com", appointmentService);

        Appointment nonExistingAppointment = new Appointment(LocalDateTime.of(2023, 6, 2, 15, 30));

        Appointment canceledAppointment = paciente.appointmentCancelation(nonExistingAppointment);

        Assertions.assertNull(canceledAppointment);

    }
}