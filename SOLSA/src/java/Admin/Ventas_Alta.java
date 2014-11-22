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

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Cliente_Alta.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("superadmin") == false)
            response.sendRedirect("../Login");

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
        String tipo = "ventas";
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        String sql = "INSERT INTO Usuario (username, password, salt, nombre, direccion, telefono, tipo, idDepartamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setInt(3, salt);
                    ps.setString(4, nombre);
                    ps.setString(5, direccion);
                    ps.setString(6, telefono);
                    ps.setString(7, tipo);
                    ps.setInt(8, idDepartamento);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                    }
                }
                if (st) {
                    request.setAttribute("res", "El ususario " + session.getAttribute("username") + " ha sido registrado exitosamente.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Cliente_Alta.jsp");
                    rd.include(request, response);
                } else {
                    request.setAttribute("res", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Cliente_Alta.jsp");
                    rd.include(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Ventas_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
