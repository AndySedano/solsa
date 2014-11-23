package Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class Ventas_Alta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login");
        }
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Ventas_Alta.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login");
        }
        
        if (request.getParameter("password").length() < 8
            || !request.getParameter("password").equals(request.getParameter("passwordagain")))
        {
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error de validación");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Ventas_Alta.jsp");
            disp.include(request, response);
            return;
        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Pair<String, Integer> hash = Helpers.Login.createNewHash(password);
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String tipo = "ventas";
        String sql = "INSERT INTO Usuario (username, password, salt, nombre, direccion, telefono, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection con = Helpers.DB.newConnection(this)) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, hash.getValue0());
                ps.setInt(3, hash.getValue1());
                ps.setString(4, nombre);
                ps.setString(5, direccion);
                ps.setString(6, telefono);
                ps.setString(7, tipo);
                ps.executeUpdate();
            }
            
            request.setAttribute("message", "El usuario " + username + " ha sido registrado exitosamente.");
            doGet(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ventas_Alta.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "true");
            request.setAttribute("message", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
            doGet(request, response);
        }
    } 
}
