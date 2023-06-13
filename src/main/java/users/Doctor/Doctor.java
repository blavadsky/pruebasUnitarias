package users.Doctor;

import appointment.Appointment;
import appointment.AppointmentService;
import java.util.ArrayList;
import java.util.List;

public class Doctor implements SpecialistDoctor, GeneralDoctor {

    private Integer id;
    private DoctorService doctorService;

    public List<Appointment> appointmenGeneralList(Integer id, AppointmentService appointmentService) {
        List<Appointment> generalAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentService.getAppointments()) {
            if (doctorService.getDoctor(id).getId() == id) {
                generalAppointments.add(appointment);
            }
        }
        return generalAppointments;
    }

    public List<Appointment> appointmenSpecialistList(Integer id, AppointmentService appointmentService) {
        List<Appointment> specialistAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentService.getAppointments()) {
            if (doctorService.getDoctor(id).getId() == id) {
                specialistAppointments.add(appointment);
            }
        }
        return specialistAppointments;
    }

    public void doctorPrescription(Integer appointmentId, String prescription) {
        Appointment appointment = doctorService.getAppointmentById(appointmentId);
        if (appointment != null) {
            appointment.setPrescription(prescription);
            System.out.println("Se ha enviado la orden médica para el appointment con ID " + appointmentId);
        } else {
            System.out.println("No se encontró un appointment con ID " + appointmentId);
        }
    }
    @Override
    public void getSpecialistDoctor() {

    }

    @Override
    public Doctor getGeneralDoctor() {
        return new Doctor(id, doctorService);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor(Integer id, DoctorService doctorService) {
        this.id = id;
        this.doctorService = doctorService;
    }


}
