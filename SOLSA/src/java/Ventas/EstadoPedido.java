package Ventas;

import Beans.Pedido;
import SuperAdmin.AddUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.javatuples.Pair;

public class EstadoPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("ventas") == false) {
            response.sendRedirect("../Login");
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            PreparedStatement ps = con.prepareStatement("SELECT Pedido.idPEdido AS id, Empresa.nombre AS Empresa, Pedido.fechaDeEntrega, Pedido.estado\n"
                    + "FROM Pedido, Empresa\n"
                    + "WHERE Pedido.Empresa_idEmpresa = Empresa.idEMpresa and Empresa.idEmpresa = ?");

            ps.setInt(1, Integer.parseInt(request.getParameter("id")));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pedido bean = new Pedido();
                bean.setId(rs.getInt("id"));
                bean.setEmpresa(rs.getString("Empresa"));
                bean.setDate(rs.getDate("fechaDeEntrega").toString());
                bean.setEstado(rs.getString("estado"));
                request.setAttribute("inf", bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/EstadoPedido.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
