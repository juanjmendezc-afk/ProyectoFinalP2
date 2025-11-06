package co.edu.uniquindio.proyectofinalp2.estructural;

import co.edu.uniquindio.proyectofinalp2.models.Cliente;

public class ClienteAdapter {

    private Cliente cliente;

    public ClienteAdapter(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return cliente.getNombre();
    }

    public String getEmail() {
        return cliente.getEmail();
    }

    public String getTelefono() {
        return cliente.getTelefono();
    }
}
