package Cliente;

import Beans.Pedido;
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

public class Peticiones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("cliente") == false) {
            response.sendRedirect("../Login"); return;
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            PreparedStatement ps = con.prepareStatement("SELECT Peticion.idPeticion AS id, fecha, estado, Carrito.Usuario_username\n"
                    + "FROM Peticion INNER JOIN Carrito ON Carrito.idCarrito = Peticion.Carrito_idCarrito\n"
                    + "Where Carrito.Usuario_username = ?;");

            ps.setString(1, session.getAttribute("username").toString());

            ResultSet rs = ps.executeQuery();
            
            ArrayList<Pedido> beans = new ArrayList<>();
            
            while (rs.next()) {
                Pedido bean = new Pedido();
                bean.setId(rs.getInt("id"));
                bean.setDate(rs.getDate("fecha").toString());
                bean.setEstado(rs.getString("estado"));
                request.setAttribute("bean", bean);
                beans.add(bean);
            }
            
            request.setAttribute("inf", beans);

        } catch (SQLException ex) {
            Logger.getLogger(EstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Peticiones.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
