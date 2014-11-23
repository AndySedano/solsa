package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig(fileSizeThreshold = 2* 1024 * 1024,  // 2MB
                 maxFileSize = 10 * 1024 * 1024,      // 10MB
                 maxRequestSize = 50 * 1024 * 1024)   // 50MB
public class Producto_Modificar extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false)
        {
            response.sendRedirect("../Login"); return;
        }
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            String sql = "select Producto.*, Fotografia.idFotografia, Fotografia.nombre as nombreFoto "
                       + "from Producto "
                       + "join Fotografia on Producto.Fotografia_idFotografia = Fotografia.idFotografia "
                       + "where idProducto = ?;";
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ResultSet rs = ps.executeQuery();
                rs.next();
                
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(Helpers.Money.toString(rs.getInt("precio")));
                producto.setPuntoDeReorden(rs.getInt("puntoDeReorden"));
                
                Foto foto = new Foto();
                foto.setNombre(rs.getString("nombreFoto"));
                foto.setUrl(Helpers.File.getFotoUrl(request, rs.getInt("idFotografia"), rs.getString("nombreFoto")));
                
                producto.setFoto(foto);
            
                request.setAttribute("producto", producto);
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Producto_Modificar.jsp");
                disp.include(request, response);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Departamento_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false)
        {
            response.sendRedirect("../Login"); return;
        }
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            String sql;
            int idFoto = 0;
            Part part = Helpers.File.getParameter(request, "foto");
            long size = part.getSize();
            if (size == 0)
            {
                sql = "update Producto set nombre = ?, descripcion = ?, precio = ?, puntoDeReorden = ? where idProducto = ?;";
            }
            else
            {
                idFoto = Helpers.File.insertarFoto(this, request, con, "foto");
                sql = "update Producto set nombre = ?, descripcion = ?, precio = ?, puntoDeReorden = ?, Fotografia_idFotografia = ? where idProducto = ?;";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setString(1, request.getParameter("nombre"));
                ps.setString(2, request.getParameter("descripcion"));
                ps.setInt(3, Helpers.Money.parse(request.getParameter("precio")));
                ps.setInt(4, Integer.parseInt(request.getParameter("puntoDeReorden")));
                if (idFoto == 0)
                {
                    ps.setInt(5, Integer.parseInt(request.getParameter("idProducto")));
                }
                else
                {
                    ps.setInt(5, idFoto);
                    ps.setInt(6, Integer.parseInt(request.getParameter("idProducto")));
                }
                ps.executeUpdate();
            }
            
            request.setAttribute("message", "Modificación exitosa");
            doGet(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Producto_Modificar.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error al modificar producto");
            doGet(request, response);
        }
    }    
}