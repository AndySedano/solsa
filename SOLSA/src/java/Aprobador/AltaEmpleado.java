package Aprobador;

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
public class AltaEmpleado extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        //request.getCharacterEncoder("UTF-8");
        String url = getInitParameter("url");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        boolean st = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int salt = Integer.parseInt(request.getParameter("salt"));
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String tipo = request.getParameter("tipo");
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        String sql = "INSERT INTO Usuario (username, password, salt, nombre, direccion, telefono, tipo, idDepartamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url,user,pass)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, telefono);
                    ps.setInt(4, salt);
                    ps.setString(5, nombre);
                    ps.setString(6, direccion);
                    ps.setString(7, telefono);
                    ps.setString(8, tipo);
                    ps.setInt(9, idDepartamento);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                    }
                } if (st) {
                    request.setAttribute("res", "El ususario " + session.getAttribute("username") + " ha sido registrado exitosamente.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("lapagina.jsp");
                    rd.include(request, response);
                } else {
                    request.setAttribute("res", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("lapagina.jsp");
                    rd.include(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AltaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
