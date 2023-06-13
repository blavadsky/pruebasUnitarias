package users;

import appointment.Appointment;
import appointment.AppointmentService;

import java.time.LocalDateTime;

public class Paciente extends User {

    protected String name;
    protected String dni;
    protected AppointmentService appointmentService;
    public Appointment appointmentRequest(LocalDateTime appointmentRequested) {
        Appointment existingAppointment = appointmentService.getAppointmentByDateTime(appointmentRequested);
        if (existingAppointment != null) {
            System.out.println("Ya se ha creado un appointment con la misma fecha y hora.");
            return existingAppointment;
        } else {
            Appointment newAppointment = new Appointment(appointmentRequested);
            appointmentService.saveAppointment(1, appointmentRequested);
            return newAppointment;
        }
    }

    public Appointment appointmentCancelation (Appointment appointmentToCancel) {
        LocalDateTime appointmentDateTime = appointmentToCancel.getDataTime();
        Appointment existingAppointment = appointmentService.getAppointmentByDateTime(appointmentDateTime);

        if (existingAppointment != null) {
            appointmentService.deleteAppointment(existingAppointment);
            return existingAppointment;
        } else {
            System.out.println("No se encontró un appointment con la misma fecha y hora.");
            return null;
        }
    }


    public boolean appointmentPay (Appointment appointment){
        boolean isPaid = appointment.isPaid();
        System.out.println("El appointment " + (isPaid ? "ya ha sido pagado." : "no ha sido pagado."));
        return isPaid;
    }


    public Paciente(String password, String email, AppointmentService appointmentService) {
        super(password, email);
        this.name = name;
        this.dni = dni;
        this.appointmentService = appointmentService;
    }


}
