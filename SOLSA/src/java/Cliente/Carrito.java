package Cliente;

import Beans.CarritoBean;
import Beans.Foto;
import Beans.Producto;
import Ventas.EstadoPedido;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Carrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("cliente") == false) {
            response.sendRedirect("../Login");
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            try (PreparedStatement ps = con.prepareStatement("SELECT idProducto AS id, Producto.nombre, descripcion, precio, tipo, Fotografia_idFotografia AS idFoto, Fotografia.nombre AS nombreFoto\n"
                    + "FROM Producto JOIN Fotografia ON Fotografia.idFotografia = Producto.Fotografia_idFotografia\n"
                    + "WHERE Producto.idProducto = ?;")) {

                ps.setInt(1, Integer.parseInt(request.getParameter("id")));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Producto bean = new Producto();
                    bean.setIdProducto(rs.getInt("id"));
                    bean.setNombre(rs.getString("nombre"));
                    bean.setDescripcion(rs.getString("descripcion"));
                    bean.setPrecio(Helpers.Money.toString(rs.getInt("precio")));
                    bean.setTipo(rs.getString("tipo"));
                    Foto f = new Foto();
                    f.setNombre(rs.getString("nombreFoto"));
                    f.setUrl(request.getContextPath() + "/Images/" + rs.getString("idFoto") + "-" + f.getNombre());
                    bean.setCantidad(1);
                    bean.setFoto(f);

                    if (session.getAttribute("carrito") == null) {
                        CarritoBean c = new CarritoBean();
                        c.agregar(bean);
                        session.setAttribute("carrito", c);
                    } else {
                        ((CarritoBean)session.getAttribute("carrito")).agregar(bean);
                    }
                }
            }
            
            if(session.getAttribute("carrito") != null){   
                request.setAttribute("inf", ((CarritoBean)session.getAttribute("carrito")).getProductos());
            }else{
                request.setAttribute("mensaje", "Carrito Vacio");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("Productos");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
