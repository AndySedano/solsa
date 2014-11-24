package Beans;

public class Cliente {
    private String username;
    private String nombre;
    private String nombreEmpresa;
    private String nombreDepartamento;
    
    public Cliente () {}
    
    public Cliente (String username, String nombre, String nombreEmpresa, String nombreDepartamento) {
        this.username = username;
        this.nombre = nombre;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreDepartamento = nombreDepartamento;
    }
    
    public String getUsername () {
        return username;
    }
    public void setUsername (String user) {
        username = user;
    }
    public String getNombre () {
        return nombre;
    } 
    public void setNombre (String nom) {
        nombre = nom;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nomEmp) {
        nombreEmpresa = nomEmp;
    } 
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }
    public void setNombreDepartamento (String nomDepto) {
        nombreDepartamento = nomDepto;
    }
    
}
