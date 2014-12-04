package Beans;

public class Peticion {
    
    private int id;
    private String responsable;
    private String date;
    private String estado;
    private int idCarrito;
    private String nombreEmpresa;
    private int numAprobadas;
    private int numRechazadas;
    private int numRealizadas;
    
    public Peticion(){
        
    }
    
    public Peticion (String nombreEmpresa, int numAprobadas, int numRechazadas, int numRealizadas) {
        this.nombreEmpresa = nombreEmpresa;
        this.numAprobadas= numAprobadas;
        this.numRechazadas = numRechazadas;
        this.numRealizadas = numRealizadas;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getNumAprobadas(){
        return numAprobadas;
    }
    
    public void setNumAprobadas(int numAprobadas){
        this.numAprobadas = numAprobadas;
    }
    
    public int getNumRechazadas(){
        return numRechazadas;
    }
    
    public void setNumRechazadas(int numRechazadas){
        this.numRechazadas = numRechazadas;
    }    
    
    public int getNumRealizadas(){
        return numRealizadas;
    }
    
    public void setNumRealizadas(int numRealizadas){
        this.numRealizadas = numRealizadas;
    }    
            
    public String getNombreEpresa(){
        return nombreEmpresa;
    }
    
    public void setNombreEmpresa(String nombreEmpresa){
        this.nombreEmpresa = nombreEmpresa;
    }
}
