package users.Doctor;

import appointment.Appointment;
import users.Doctor.Doctor;
import users.Doctor.DoctorRepository;

import java.util.HashMap;
import java.util.Map;

public class DoctorService {
    Map<Integer, Appointment> appointments = new HashMap<>();
    private DoctorRepository doctorRepository;
    Map<Integer, Doctor> doctors = new HashMap<>();

    public Doctor getDoctor(Integer id) {
        return doctorRepository.getDoctorR(id);
    }

    public Appointment getAppointmentById(Integer appointmentId) {

        return appointments.get(appointmentId);
    }
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
}
