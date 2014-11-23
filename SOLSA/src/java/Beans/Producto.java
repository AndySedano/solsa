package Beans;

public class Producto
{
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int precio;
    private int puntoDeReorden;
    private Foto foto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPuntoDeReorden() {
        return puntoDeReorden;
    }

    public void setPuntoDeReorden(int puntoDeReorden) {
        this.puntoDeReorden = puntoDeReorden;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
}
