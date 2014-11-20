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
public class Buscar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Buscar.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String pass = getInitParameter("pass");
        String nombre = request.getParameter("nombre");
        boolean st = false;
        String sql = "SELECT username,nombre,tipo,idDepartamento FROM Usuario WHERE nombre LIKE ?";

        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                    }
                }
            }
            if (st) {
                request.setAttribute("res", "El usuario " + session.getAttribute("username") + " ha sido modificado exitosamente.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("modifica.... .jsp");
                rd.include(request, response);
            } else {
                request.setAttribute("res", "Lo sentimos, hubo un error, seleccione nuevamente.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("modifica.... .jsp");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
