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
public class Empresa_Alta extends HttpServlet
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
            response.sendRedirect("../Login");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Empresa_Alta.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false)
            response.sendRedirect("../Login");
        
        String path = getServletContext().getRealPath("") + File.separator + "Images";
        File fileSaveDir = new File(path);
        if (!fileSaveDir.exists())
        {
            fileSaveDir.mkdir();
        }
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            int idFoto = 0;
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO Fotografia (nombre, imagen) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS))
            {
                Part foto = Helpers.File.getParameter(request, "foto");
                String fileName = Helpers.File.extractFileName(foto);
                ps.setString(1, fileName);
                ps.setBlob(2, (InputStream)null);
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys())
                {
                    keys.next();
                    idFoto = keys.getInt(1);
                    foto.write(path + File.separator + idFoto + "-" + fileName);
                }
            }
            try (PreparedStatement ps =
                con.prepareStatement("INSERT INTO Empresa (nombre, direccion, telefono, RFC, Fotografia_idFotografia) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, request.getParameter("nombre"));
                ps.setString(2, request.getParameter("direccion"));
                ps.setString(3, request.getParameter("telefono"));
                ps.setString(4, request.getParameter("rfc"));
                ps.setInt(5, idFoto);
                ps.executeUpdate();
            }
            
            request.setAttribute("message", "Alta de empresa exitosa");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Empresa_Alta.jsp");
            disp.include(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Empresa_Alta.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error al dar de alta empresa");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Empresa_Alta.jsp");
            disp.include(request, response);
        }
    }
}