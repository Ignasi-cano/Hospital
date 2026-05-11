package domini;

import java.time.LocalDate;

public class Persona {
	
	private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;

    public Persona(Integer id, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona(String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
        this(null, nombre, apellido1, apellido2, fechaNacimiento);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getApellido1() { return apellido1; }
    public String getApellido2() { return apellido2; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    @Override
    public String toString() {
        return "Persona{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", apellido1='" + apellido1 + '\'' +
               ", apellido2='" + apellido2 + '\'' +
               ", fechaNacimiento=" + fechaNacimiento +
               '}';
    }

}
