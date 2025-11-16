package co.edu.uniquindio.proyectofinalp2.models;

import co.edu.uniquindio.proyectofinalp2.comportamiento.Observer;

public class User implements Observer {

    private String id;
    private String nombreCompleto;
    private String email;
    private String password;
    private String telefono;
    private String rol;

    private String tipoIdentificacion;
    private String numeroIdentificacion;

    private String estado;

    // Constructor completo
    public User(String id, String nombreCompleto, String email, String password,
                String telefono, String rol,
                String tipoIdentificacion, String numeroIdentificacion,
                String estado) {

        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.estado = estado;
    }

    // Constructor secundario
    public User(String id, String nombre, String email, String password, String telefono, String rol) {
        this.id = id;
        this.nombreCompleto = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
        this.tipoIdentificacion = "";
        this.numeroIdentificacion = "";
        this.estado = "ACTIVO";
    }

    // ==========================
    // IMPLEMENTACIÓN OBSERVER
    // ==========================

    @Override
    public void recibirNotificacion(String mensaje) {
        System.out.println("📩 Notificación para " + nombreCompleto + ": " + mensaje);
    }

    // GETTERS Y SETTERS

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public void setTipoIdentificacion(String tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return nombreCompleto + " (" + email + ")";
    }
}
