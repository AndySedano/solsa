package Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 *
 * @author David
 */
public class Cliente_Modificar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Cliente_Modificacion.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String pass = getInitParameter("pass");
        String username = request.getParameter("username");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        boolean st = false;
        String sql = "UPDATE Usuario SET nombre=?, direccion=?, telefono=? WHERE username=?;";

        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, direccion);
                    ps.setString(3, telefono);
                    ps.setString(4, username);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                        session.setAttribute("username", session.getAttribute("username"));
                    }
                }
                if (st) {
                    request.setAttribute("res", "El usuario " + session.getAttribute("username") + " ha siido modificado.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Cliente_Modificacion.jsp");
                    rd.include(request, response);
                            
                } else {
                    request.setAttribute("res", "Lo sentimos, ha ocurrido un error, ingrese los datos nuevamente...");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Cliente_Modificacion.jsp");
                    rd.include(request, response);
                }
                con.close();
            } 
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cliente_Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}