package Cliente;

import Beans.Foto;
import Beans.Pedido;
import Beans.Producto;
import Ventas.EstadoPedido;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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

            try (PreparedStatement ps = con.prepareStatement("SELECT idProducto AS id, nombre, descripcion, precio, tipo, Fotografia_idFotografia AS idFoto, Fotografia.nombre as nombreFoto\n"
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
                    bean.setFoto(f);
                }
            }

            PreparedStatement ps2 = con.prepareStatement("SELECT Carrito_idCarrito, Producto.idProducto AS id, nombre, cantidad\n"
                    + "FROM Carrito_Producto JOIN Producto ON Carrito_Producto.idProducto = Producto.idProducto\n"
                    + "WHERE Carrito_idCarrito = ?;");

            //ps2.setInt(1, idC);

            ResultSet rs2 = ps2.executeQuery();

            ArrayList<Producto> beans = new ArrayList<>();

            while (rs2.next()) {
                Producto bean = new Producto();
                bean.setIdProducto(rs2.getInt("id"));
                bean.setNombre(rs2.getString("nombre"));
                bean.setDescripcion(Integer.toString(rs2.getInt("cantidad")));
                beans.add(bean);
            }
            request.setAttribute("inf", beans);

        } catch (SQLException ex) {
            Logger.getLogger(EstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Carrito.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
