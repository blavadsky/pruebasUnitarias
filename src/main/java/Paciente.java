public class Paciente extends User {

    protected String name;
    protected String dni;

    public Paciente(String phoneNumber, String password, String email) {
        super(phoneNumber, password, email);
        this.name = name;
        this.dni = dni;
    }


}
