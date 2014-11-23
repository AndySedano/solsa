package Ventas;

import Beans.Pedido;
import Beans.Producto;
import SuperAdmin.AddUser;
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

public class EstadoPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("ventas") == false) {
            response.sendRedirect("../Login"); return;
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            PreparedStatement ps = con.prepareStatement("SELECT Pedido.idPedido AS id, Empresa.nombre AS Empresa, Pedido.fechaDeEntrega, Pedido.estado, Carrito_idCarrito as idC\n"
                    + "FROM Pedido, Empresa\n"
                    + "WHERE Pedido.Empresa_idEmpresa = Empresa.idEmpresa and Empresa.idEmpresa = ?");

            ps.setInt(1, Integer.parseInt(request.getParameter("id")));

            ResultSet rs = ps.executeQuery();

            int idC = 0;
            if (rs.next()) {
                Pedido bean = new Pedido();
                bean.setId(rs.getInt("id"));
                bean.setEmpresa(rs.getString("Empresa"));
                bean.setDate(rs.getDate("fechaDeEntrega").toString());
                bean.setEstado(rs.getString("estado"));
                idC = rs.getInt("idC");
                request.setAttribute("bean", bean);
            }

            PreparedStatement ps2 = con.prepareStatement("SELECT Carrito_idCarrito, Producto.idProducto AS id, nombre, cantidad\n"
                    + "FROM Carrito_Producto JOIN Producto ON Carrito_Producto.idProducto = Producto.idProducto\n"
                    + "WHERE Carrito_idCarrito = ?;");

            ps2.setInt(1, idC);

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

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Ventas/EstadoPedido.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("ventas") == false) {
            response.sendRedirect("../Login"); return;
        }
        
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection con = Helpers.DB.newConnection(this)) {

            try (PreparedStatement ps = con.prepareStatement("UPDATE Pedido SET estado=? WHERE idPedido = ?;")) {
                ps.setString(1, request.getParameter("estado"));
                ps.setInt(2, id);

                ps.executeUpdate();

            }

        } catch (SQLException ex) {
            Logger.getLogger(EstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("mensaje" , "Cambio realizado con exito!");
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Ventas/Pedidos.jsp");
        disp.include(request, response);
    }
}
