package co.edu.uniquindio.proyectofinalp2.models;

public class User {
    private String idUsuario;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String tipo; // "USUARIO" o "ADMIN"

    // Constructor vacío (necesario para FXML)
    public User() {
    }

    // Constructor completo
    public User(String idUsuario, String nombre, String email, String password, String telefono, String tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    // Método para validar credenciales
    public boolean validarCredenciales(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    // Getters y Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}