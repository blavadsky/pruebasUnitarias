package appointment;

import java.time.LocalDateTime;

public interface AppointmentRepository {
    Appointment saveAppointmentR(Integer id, Appointment appointment);

    Appointment getAppointmentR(Integer id);

    Appointment updateAppointmentR(Integer id, Appointment appointment);

    Appointment deleteAppointmentR(Appointment appointment);

    Appointment getAppointmentByDateTime(LocalDateTime appointmentDateTime);

}
