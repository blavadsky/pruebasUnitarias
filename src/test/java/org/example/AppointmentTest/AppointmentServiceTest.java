package org.example.AppointmentTest;

import appointment.Appointment;
import appointment.AppointmentRepository;
import appointment.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    void saveAppointment() {
        Appointment appointmentEsperado =
                new Appointment(LocalDateTime.of(2023,5,30,14,30));

        AppointmentService appointmentService =
                new AppointmentService(appointmentRepository);

        Mockito.lenient().when(appointmentRepository.saveAppointmentR(1,appointmentEsperado)).thenReturn(
                new Appointment(LocalDateTime.of(2023,5,30,14,30)));

        Appointment appointmentResultado = appointmentService.saveAppointment(1,LocalDateTime.of(2023,5,30,14,30));

        Assertions.assertEquals(appointmentEsperado, appointmentResultado);
    }

    @Test
    void getAppointment() {
        Appointment appointmentEsperado =
                new Appointment(LocalDateTime.of(2023,5,30,14,30));
        AppointmentService appointmentService =
                new AppointmentService(appointmentRepository);

        appointmentService.saveAppointment(1,appointmentEsperado);

        Mockito.when(appointmentRepository.getAppointmentR(1)).thenReturn(appointmentEsperado);

        Appointment appointmentResultado = appointmentService.getAppointment(1);
        Assertions.assertEquals(appointmentEsperado, appointmentResultado);
    }

   @Test
    void updateAppointment() {
        Appointment appointmentInicial =
                new Appointment(LocalDateTime.of(2023,5,30,14,30));
        AppointmentService appointmentService =
                new AppointmentService(appointmentRepository);
        appointmentService.saveAppointment(1,appointmentInicial);

       Appointment appointmentActualizado =
               new Appointment(LocalDateTime.of(2023,5,30,14,55));
        appointmentService.updateAppointment(1,appointmentActualizado);

       Appointment citaActualizadaObtenida = appointmentService.getAppointment(1);
       Appointment citaActualizadaRepo = appointmentRepository.getAppointmentR(1);

        Mockito.lenient().when(appointmentRepository.updateAppointmentR(1,appointmentActualizado)).thenReturn(appointmentActualizado);
    }

    @Test
    void delete() {
        Appointment appointment =
                new Appointment(LocalDateTime.of(2023, 5, 30, 14, 30));

        AppointmentService appointmentService =
                new AppointmentService(appointmentRepository);
        appointmentService.saveAppointment(1, appointment);


        Mockito.when(appointmentRepository.deleteAppointmentR(appointment)).thenReturn(appointment);
        boolean result = appointmentService.deleteAppointment(appointment);

        Mockito.verify(appointmentRepository, Mockito.times(1)).deleteAppointmentR(appointment);

        Assertions.assertNotEquals(false, result);
    }

}