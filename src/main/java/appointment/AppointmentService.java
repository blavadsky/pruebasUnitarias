package appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    Map<Integer, Appointment> appointments = new HashMap<>();

    public Appointment saveAppointment(Integer id, LocalDateTime availableAppointment) {
        if(appointments.get(id) == null) {
        Appointment appointment = new Appointment(availableAppointment);
        appointments.put(id, appointment);
        return appointment;
        }
        return appointments.get(id);
    }

    public Appointment saveAppointment(Integer id, Appointment appointment) {
        appointments.put(id, appointment);
        return appointment;
    }

    public Appointment getAppointment(Integer appointmentId) {
        return appointmentRepository.getAppointmentR(appointmentId);
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments.values());
    }
    public Appointment updateAppointment(Integer id, Appointment appointment){
        appointments.replace(id,appointment);
        return appointmentRepository.updateAppointmentR(id,appointment);
    }

    public boolean deleteAppointment(Appointment appointment) {
        return appointmentRepository.deleteAppointmentR(appointment) != null;
    }

    public Appointment getAppointmentByDateTime(LocalDateTime appointmentDateTime) {
        for (Appointment appointment : appointments.values()) {
            if (appointmentRepository.getAppointmentByDateTime(appointmentDateTime).equals(appointmentDateTime)) {
                return appointment;
            }
        }
        return null;
    }

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
}

