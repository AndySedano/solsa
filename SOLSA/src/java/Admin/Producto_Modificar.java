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

public class Producto_Modificar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login"); return;
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Producto_Modificacion.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("superadmin") == false)
        {
            response.sendRedirect("../Login"); return;
        }

        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String pass = getInitParameter("pass");

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int precio = Integer.parseInt(request.getParameter("precio"));
        int puntoDeReorden = Integer.parseInt(request.getParameter("puntoDeReorden"));
        String tipo = request.getParameter("tipo");
        int fotografia_idFotografia = Integer.parseInt(request.getParameter("fotografia_idFotografia"));
        
        
        boolean st = false;
        String sql = "UPDATE Producto SET nombre=?, descripcion=?, precio=?, puntoDeReorden=?, tipo=?, fotografia_idFotografia=? WHERE username=?;";

        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, descripcion);
                    ps.setInt(3, precio);
                    ps.setInt(4, puntoDeReorden);
                    ps.setString(5, tipo);
                    ps.setInt(6, fotografia_idFotografia);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                        session.setAttribute("nombre", session.getAttribute("nombre"));
                    }
                }
                if (st) {
                    request.setAttribute("res", "El producto " + session.getAttribute("nombre") + " ha sido modificado.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/Producto_Modificacion.jsp");
                    rd.include(request, response);
                            
                } else {
                    request.setAttribute("res", "Lo sentimos, ha ocurrido un error, ingrese los datos nuevamente...");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/Producto_Modificacion.jsp");
                    rd.include(request, response);
                }
                con.close();
            } 
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Producto_Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}


