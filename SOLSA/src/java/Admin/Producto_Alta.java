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

public class Producto_Alta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false)
            response.sendRedirect("../Login");
        
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Producto_Alta.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String url = getInitParameter("url");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        boolean st = false;
        String idProducto = request.getParameter("idProducto");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int precio = Integer.parseInt(request.getParameter("precio"));
        int puntoDeReorden = Integer.parseInt(request.getParameter("puntoDeReorden"));
        String tipo = request.getParameter("tipo");
        int fotografia_idFotografia = Integer.parseInt(request.getParameter("fotografia_idFotografia"));
        
        String sql = "INSERT INTO Producto (idProducto, nombre, descripcion, precio, puntoDeReorden, tipo, fotografia_idFotografia) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, idProducto);
                    ps.setString(2, nombre);
                    ps.setString(3, descripcion);
                    ps.setInt(4, precio);
                    ps.setInt(5, puntoDeReorden);
                    ps.setString(6, tipo);
                    ps.setInt(7, fotografia_idFotografia);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                        session.setAttribute("username", session.getAttribute("username"));
                    }
                }
                if (st) {
                    request.setAttribute("res", "El producto " + session.getAttribute("nombre") + " ha sido registrado exitosamente.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Producto_Alta.jsp");
                    rd.include(request, response);
                } else {
                    request.setAttribute("res", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Producto_Alta.jsp");
                    rd.include(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cliente_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
