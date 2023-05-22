package org.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    Map<Integer, Appointment> appointments = new HashMap<>();

    public Appointment saveAppointment(Integer id, LocalDateTime availableAppointment) {
        if(appointments.get(id) == null ){
        appointments.put(id, new Appointment(id,availableAppointment));
        }
        return appointments.get(id);
    }

    public Appointment saveAppointment(Appointment appointment) {
            return appointments.get(appointment.getId());
    }

    public Appointment getAppointment(Integer id) {

        return appointments.get(id);

    }

    public Appointment updateAppointment(Appointment appointment){
        return appointments.replace(appointment.getId(), appointment);
    }

    public boolean delete(Appointment appointment) {
        return appointments.remove(appointment.getId()) != null;
    }
}

