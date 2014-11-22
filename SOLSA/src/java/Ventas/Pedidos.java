package Ventas;

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

public class Pedidos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("ventas") == false) {
            response.sendRedirect("../Login");
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
            response.sendRedirect("../Login");
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            Class.forName("com.mysql.jdbc.Driver");

            String busqueda = request.getParameter("loquequieras");
            String dato = request.getParameter("busqueda");

            PreparedStatement ps = con.prepareStatement("SELECT Pedido.idPedido AS id, Empresa.nombre AS Empresa, Pedido.fechaDeEntrega, Pedido.estado \n"
                    + "FROM Pedido, Empresa WHERE Pedido.Empresa_idEmpresa = Empresa.idEMpresa and ? = ?;");
            ps.setString(1, busqueda);
            ps.setString(2, dato);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MyBean bean = new MyBean();
                bean.setName(rs.getString(1));
                bean.setSurname(rs.getString(2));
                request.setAttribute("inf", bean);
            }

            RequestDispatcher disp = request.getRequestDispatcher("Pedidos.jsp");
            disp.include(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Ventas/Pedidos.jsp");
        disp.include(request, response);

    }
}
