package Beans;

public class Departamento {
    
    private int idDepartamento;
    private String nombreDepartamento;
    private String nombreEmpresa;
    
    public Departamento () {}
    
    public Departamento(int idDepartamento, String nombreDepartamento, String nombreEmpresa) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.nombreEmpresa = nombreEmpresa;
    }
    
    public int getIdDepartamento () {
        return idDepartamento;
    }
    public void setIdDepartamento (int idDepto) {
        idDepartamento = idDepto;
    }
    public String getNombreDepartamento () {
        return nombreDepartamento;
    }
    public void setNombreDepartamento (String nomDepto) {
        nombreDepartamento = nomDepto;
    }
    public String getNombreEmpresa () {
        return nombreEmpresa;
    }
    public void setNombreEmpresa (String nomEmp) {
        nombreEmpresa = nomEmp;
    }
}
