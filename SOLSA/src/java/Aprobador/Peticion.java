package Aprobador;

import Beans.Producto;
import Ventas.Pedidos;
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

public class Peticion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("aprobador") == false) {
            response.sendRedirect("../Login");
            return;
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            int idCarrito = 0;
            try (PreparedStatement ps = con.prepareStatement("select * from Peticion where idPeticion = ?;")) {
                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Beans.Peticion peticion = new Beans.Peticion();
                    peticion.setId(rs.getInt("idPeticion"));
                    peticion.setDate(rs.getString("fecha"));
                    peticion.setEstado(rs.getString("estado"));
                    idCarrito = rs.getInt("Carrito_idCarrito");
                    peticion.setIdCarrito(idCarrito);
                    request.setAttribute("peticion", peticion);
                }
            }

            try (PreparedStatement ps = con.prepareStatement("SELECT Producto.idProducto AS id, Producto.nombre, cantidad\n"
                    + "FROM Producto JOIN Carrito_Producto ON Producto.idProducto = Carrito_Producto.idProducto\n"
                    + "WHERE Carrito_Producto.Carrito_idCarrito = ?;")) {

                ps.setInt(1, idCarrito);
                ResultSet rs = ps.executeQuery();
                
                ArrayList<Producto> productos = new ArrayList<>();

                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                    productos.add(producto);
                }
                request.setAttribute("productos", productos);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Peticion.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Aprobador/Peticion.jsp");
        disp.include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("aprobador") == false) {
            response.sendRedirect("../Login");
            return;
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            Class.forName("com.mysql.jdbc.Driver");

            String query = request.getParameter("loquequieras").equals("1") ? "Empresa.nombre" : "Pedido.Estado";

            /*PreparedStatement ps = con.prepareStatement("SELECT Pedido.idPEdido AS id, Empresa.nombre AS Empresa, Pedido.fechaDeEntrega, Pedido.estado\n"
                    + "FROM Pedido, Empresa\n"
                    + "WHERE Pedido.Empresa_idEmpresa = Empresa.idEMpresa and " + query + " = ?");

            ps.setString(1, request.getParameter("busqueda"));

            ResultSet rs = ps.executeQuery();
            ArrayList<Pedido> beans = new ArrayList<>();

            if (rs.next()) {
                Pedido bean = new Pedido();
                bean.setId(rs.getInt("id"));
                bean.setEmpresa(rs.getString("Empresa"));
                bean.setDate(rs.getDate("fechaDeEntrega").toString());
                bean.setEstado(rs.getString("estado"));
                beans.add(bean);
            }

            request.setAttribute("inf", beans);*/

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = request.getRequestDispatcher("Pedidos.jsp");
        disp.include(request, response);

    }
}
