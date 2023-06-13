package appointment;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AppointmentBD implements AppointmentRepository {

    Map<Integer, Appointment> appointments = new HashMap<>();
    @Override
    public Appointment saveAppointmentR(Integer id,Appointment appointment) {return null;}

    @Override
    public Appointment getAppointmentR(Integer id) { return appointments.get(id); }

    @Override
    public Appointment updateAppointmentR(Integer id, Appointment appointment) { return appointments.replace(appointment.getId(), appointment);}

    @Override
    public Appointment deleteAppointmentR(Appointment appointment) { return appointments.remove(1); }

    @Override
    public Appointment getAppointmentByDateTime(LocalDateTime appointmentDateTime) {
        for (Appointment appointment : appointments.values()) {
            if (appointment.getDataTime().equals(appointmentDateTime)) {
                return appointment;
            }
        }
        return null;
    }
}



