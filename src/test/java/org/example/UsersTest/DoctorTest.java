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
import users.Doctor.Doctor;
import users.Doctor.DoctorRepository;
import users.Doctor.DoctorService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorTest {
    @Mock
    private DoctorService doctorService;
    private Doctor doctor;
    private Doctor doctor1;
    private AppointmentService appointmentService;
    private Appointment appointment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        doctor1 = new Doctor(1, doctorService);
        doctor = new Doctor(1, new DoctorService(new DoctorRepository() {
            @Override
            public Doctor getDoctorR(Integer id) { return doctor.getGeneralDoctor(); }
        }));
        appointment = new Appointment(LocalDateTime.of(2023,5,30,14,30));
        appointmentService = mock(AppointmentService.class);
    }

    @Test
    void appointmenGeneralList() {
        Appointment appointment1 = new Appointment(LocalDateTime.of(2023,5,30,14,30));
        Appointment appointment2 = new Appointment(LocalDateTime.of(2023,5,30,15,00));

        when(appointmentService.getAppointments()).thenReturn(List.of(appointment1, appointment2));

        List<Appointment> result = doctor.appointmenGeneralList(1, appointmentService);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(appointment1));
        Assertions.assertTrue(result.contains(appointment2));

        verify(appointmentService, times(1)).getAppointments();
    }

    @Test
    void appointmenSpecialistList() {
        Appointment appointment1 = new Appointment(LocalDateTime.of(2023,5,30,14,30));
        Appointment appointment2 = new Appointment(LocalDateTime.of(2023,5,30,15,00));

        when(appointmentService.getAppointments()).thenReturn(List.of(appointment1, appointment2));

        List<Appointment> result = doctor.appointmenSpecialistList(1, appointmentService);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(appointment1));
        Assertions.assertTrue(result.contains(appointment2));

        verify(appointmentService, times(1)).getAppointments();

    }

    @Test
    void doctorPrescription_ValidAppointmentId() {
        int appointmentId = 1;
        String prescription = "Prescription";
        when(doctorService.getAppointmentById(appointmentId)).thenReturn(appointment);

        doctor1.doctorPrescription(appointmentId, prescription);

        Assertions.assertEquals(prescription, appointment.getPrescription());
        verify(doctorService, times(1)).getAppointmentById(appointmentId);
    }

    @Test
    void doctorPrescription_InvalidAppointmentId() {
        int appointmentId = 1;
        String prescription = "Prescription";
        when(doctorService.getAppointmentById(appointmentId)).thenReturn(null);

        doctor1.doctorPrescription(appointmentId, prescription);

        verify(doctorService, times(1)).getAppointmentById(appointmentId);
    }
}