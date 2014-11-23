package Admin;

import java.io.*;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig(fileSizeThreshold = 2* 1024 * 1024,  // 2MB
                 maxFileSize = 10 * 1024 * 1024,      // 10MB
                 maxRequestSize = 50 * 1024 * 1024)   // 50MB
public class Producto_Alta extends HttpServlet
{
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
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login");
        }
        
        String path = Helpers.File.createImageFolder(this);
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            int idFoto = Helpers.File.insertarFoto(this, request, con, "foto");
            try (PreparedStatement ps =
                con.prepareStatement("INSERT INTO Producto (nombre, descripcion, precio, puntoDeReorden, Fotografia_idFotografia) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, request.getParameter("nombre"));
                ps.setString(2, request.getParameter("descripcion"));
                ps.setInt(3, Helpers.Money.parse(request.getParameter("precio")));
                ps.setInt(4, Integer.parseInt(request.getParameter("puntoDeReorden")));
                ps.setInt(5, idFoto);
                ps.executeUpdate();
            }
            
            request.setAttribute("message", "Alta de producto exitoso");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Producto_Alta.jsp");
            disp.include(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Producto_Alta.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error al dar de alta producto");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Producto_Alta.jsp");
            disp.include(request, response);
        }
        
//        String url = getInitParameter("url");
//        String user = request.getParameter("user");
//        String pass = request.getParameter("pass");
//        boolean st = false;
//        String nombre = request.getParameter("nombre");
//        String descripcion = request.getParameter("descripcion");
//        int precio = Integer.parseInt(request.getParameter("precio"));
//        int puntoDeReorden = Integer.parseInt(request.getParameter("puntoDeReorden"));
//        String tipo = request.getParameter("tipo");
//        int fotografia_idFotografia = Integer.parseInt(request.getParameter("fotografia_idFotografia"));
//        
//        String sql = "INSERT INTO Producto (nombre, descripcion, precio, puntoDeReorden, tipo, fotografia_idFotografia) VALUES (?, ?, ?, ?, ?, ?)";
//        
//        try {
//            Class.forName("con.mysql.jdbc.Driver");
//            try (Connection con = DriverManager.getConnection(url, user, pass)) {
//                try (PreparedStatement ps = con.prepareStatement(sql)) {
//                    ps.setString(1, nombre);
//                    ps.setString(2, descripcion);
//                    ps.setInt(3, precio);
//                    ps.setInt(4, puntoDeReorden);
//                    ps.setString(5, tipo);
//                    ps.setInt(6, fotografia_idFotografia);
//                    ResultSet rs = ps.executeQuery();
//                    while (rs.next()) {
//                        st = true;
//                        session.setAttribute("nombre", session.getAttribute("nombre"));
//                    }
//                }
//                if (st) {
//                    request.setAttribute("res", "El producto " + session.getAttribute("nombre") + " ha sido registrado exitosamente.");
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Producto_Alta.jsp");
//                    rd.include(request, response);
//                } else {
//                    request.setAttribute("res", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Producto_Alta.jsp");
//                    rd.include(request, response);
//                }
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(Producto_Alta.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
