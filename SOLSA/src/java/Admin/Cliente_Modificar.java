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
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        // Sobre seleccion pienso que seria bueno hacer una modificacion 
        // por cada elemento, ya sea dentro de la misma pagina web o en 
        // una particular para cada modificacion
        int seleccion = Integer.parseInt(request.getParameter("seleccion"));
        boolean st = false;
        String sql = "UPDATE Usuario SET nombre=? WHERE username=?;";

        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, username);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                    }
                }
                switch (seleccion) {
                    case 1: 
                        RequestDispatcher rd1 = getServletContext().getRequestDispatcher("Cliente_Modificacion_Nombre.jsp");
                        rd1.include(request, response);
                    case 2:
                        RequestDispatcher rd2 = getServletContext().getRequestDispatcher("Cliente_Modificacion_Salt.jsp");
                        rd2.include(request, response);
                    case 3: 
                        RequestDispatcher rd3 = getServletContext().getRequestDispatcher("Cliente_Modificacion_Direccion.jsp");
                        rd3.include(request, response);
                    case 4:
                        RequestDispatcher rd4 = getServletContext().getRequestDispatcher("Cliente_Modificacion_Telefono.jsp");
                        rd4.include(request, response);
                    case 5:
                        RequestDispatcher rd5 = getServletContext().getRequestDispatcher("Cliente_Modificacion_Tipo.jsp");
                        rd5.include(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cliente_Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}