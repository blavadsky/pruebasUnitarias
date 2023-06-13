package users.Doctor;


import java.util.HashMap;
import java.util.Map;

public class DoctorBD implements DoctorRepository {

    Map<Integer, Doctor> doctors = new HashMap<>();
    @Override
    public Doctor getDoctorR(Integer id) {
        return doctors.get(id);
    }

}
