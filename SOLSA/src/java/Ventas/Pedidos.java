package Ventas;

import Beans.Pedido;
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

public class Pedidos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("ventas") == false) {
            response.sendRedirect("../Login"); return;
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Ventas/Pedidos.jsp");
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

        try (Connection con = Helpers.DB.newConnection(this)) {

            Class.forName("com.mysql.jdbc.Driver");

            String query = request.getParameter("loquequieras").equals("1") ? "Empresa.nombre" : "Pedido.estado";

            PreparedStatement ps = con.prepareStatement("SELECT Pedido.idPedido AS id, Empresa.nombre AS Empresa, Pedido.fechaDeEntrega, Pedido.estado\n"
                    + "FROM Pedido, Empresa\n"
                    + "WHERE Pedido.Empresa_idEmpresa = Empresa.idEMpresa and " + query + " = ?");

            ps.setString(1, request.getParameter("busqueda"));

            ResultSet rs = ps.executeQuery();
            ArrayList<Pedido> beans = new ArrayList<>();
            
            Object date;
            while (rs.next()) {
                Pedido bean = new Pedido();
                bean.setId(rs.getInt("id"));
                bean.setEmpresa(rs.getString("Empresa"));
                date = rs.getDate("fechaDeEntrega");
                if(rs.wasNull()){
                    bean.setDate("indeterminado");
                }else{
                    bean.setDate(date.toString());
                }
                bean.setEstado(rs.getString("estado"));
                beans.add(bean);
            }

            request.setAttribute("inf", beans);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = request.getRequestDispatcher("Pedidos.jsp");
        disp.include(request, response);

    }
}
