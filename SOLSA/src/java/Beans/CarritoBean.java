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
        if (r != null) {
            Productos.remove(r);
        }

    }

    public void actualizar(String[] ids, String[] quantas) {
        for (Producto p : Productos) {
            for (int x = 0; x < ids.length; x++) {
                if (p.getIdProducto() == Integer.parseInt(ids[x])) {
                    p.setCantidad(Integer.parseInt(quantas[x]));
                    break;
                }
            }
        }
    }
}
