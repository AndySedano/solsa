package Beans;

import java.util.ArrayList;

public class CarritoBean {

    ArrayList<Producto> Productos = new ArrayList<>();

    public ArrayList<Producto> getProductos() {
        return Productos;
    }

    public void setProductos(ArrayList<Producto> Productos) {
        this.Productos = Productos;
    }

    public void agregar(Producto p) {
        Productos.add(p);
    }

    public void remove(int id) {
        Producto r = null;
        for (Producto p : Productos) {
            if (p.getIdProducto() == id) {
                r = p;
            }
        }
        if (r != null) 
            Productos.remove(r);
        
    }
}