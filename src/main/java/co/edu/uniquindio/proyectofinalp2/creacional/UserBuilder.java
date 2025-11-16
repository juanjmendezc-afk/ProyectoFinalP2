package co.edu.uniquindio.proyectofinalp2.creacional;

import co.edu.uniquindio.proyectofinalp2.models.User;

public class UserBuilder {

    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String password;
    private String rol;
    private String tipoId;
    private String numeroId;
    private String estado;

    public UserBuilder id(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder rol(String rol) {
        this.rol = rol;
        return this;
    }

    public UserBuilder tipoId(String tipoId) {
        this.tipoId = tipoId;
        return this;
    }

    public UserBuilder numeroId(String numeroId) {
        this.numeroId = numeroId;
        return this;
    }

    public UserBuilder estado(String estado) {
        this.estado = estado;
        return this;
    }

    public User build() {
        return new User(
                id,
                nombre,
                email,
                password,
                telefono,
                rol,
                tipoId,
                numeroId,
                estado
        );
    }
}
