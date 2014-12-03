package Beans;

public class Aprobador {
    private String username;
    private String nombre;
    private String direccion;
    private String telefono;
    
    public Aprobador() {
        
    }
    
    public Aprobador(String username, String nombre, String direccion, String telefono) {
        this.username = username;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername (String uname) {
        username = uname;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nom) {
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
    public void setTelefono(String tel) {
        telefono = tel;
    }
}
