package Beans;

public class ClienteModifica {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String nombre;
    private String direccion;
    private String telefono;
    
    public ClienteModifica () {}
    
    public ClienteModifica (String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public String getNombre () {
        return nombre;
    } 
    public void setNombre (String nom) {
        nombre = nom;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String dir) {
        direccion = dir;
    } 
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono (String tel) {
        telefono = tel;
    }
    
}
