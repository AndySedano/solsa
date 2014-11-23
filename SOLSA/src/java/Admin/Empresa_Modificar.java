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
public class Empresa_Modificar extends HttpServlet
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
            String sql = "select Empresa.*, Fotografia.idFotografia, Fotografia.nombre as nombreFoto "
                       + "from Empresa "
                       + "join Fotografia on Empresa.Fotografia_idFotografia = Fotografia.idFotografia "
                       + "where idEmpresa = ?;";
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ResultSet rs = ps.executeQuery();
                rs.next();
                
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setDireccion(rs.getString("direccion"));
                empresa.setTelefono(rs.getString("telefono"));
                empresa.setRfc(rs.getString("RFC"));
                
                Foto foto = new Foto();
                foto.setNombre(rs.getString("nombreFoto"));
                foto.setUrl(Helpers.File.getFotoUrl(request, rs.getInt("idFotografia"), rs.getString("nombreFoto")));
                
                empresa.setFoto(foto);
            
                request.setAttribute("empresa", empresa);
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Empresa_Modificar.jsp");
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
                sql = "update Empresa set nombre = ?, direccion = ?, telefono = ?, RFC = ? where idEmpresa = ?;";
            }
            else
            {
                idFoto = Helpers.File.insertarFoto(this, request, con, "foto");
                sql = "update Empresa set nombre = ?, direccion = ?, telefono = ?, RFC = ?, Fotografia_idFotografia = ? where idEmpresa = ?;";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setString(1, request.getParameter("nombre"));
                ps.setString(2, request.getParameter("direccion"));
                ps.setString(3, request.getParameter("telefono"));
                ps.setString(4, request.getParameter("rfc"));
                if (idFoto == 0)
                {
                    ps.setInt(5, Integer.parseInt(request.getParameter("idEmpresa")));
                }
                else
                {
                    ps.setInt(5, idFoto);
                    ps.setInt(6, Integer.parseInt(request.getParameter("idEmpresa")));
                }
                ps.executeUpdate();
            }
            
            request.setAttribute("message", "Modificación exitosa");
            doGet(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Empresa_Alta.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error al modificar empresa");
            doGet(request, response);
        }
    }    
}