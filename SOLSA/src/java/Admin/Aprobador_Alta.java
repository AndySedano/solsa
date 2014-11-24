package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.javatuples.*;

public class Aprobador_Alta extends HttpServlet
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
            List<Empresa> empresas = new ArrayList<>();
            try (PreparedStatement ps = con.prepareStatement("select idEmpresa, nombre from Empresa;"))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Empresa empresa = new Empresa();
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNombre(rs.getString("nombre"));
                    
                    empresas.add(empresa);
                }
            }
            
            request.setAttribute("empresas", empresas);
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Aprobador_Alta.jsp");
            disp.include(request, response);   
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Aprobador_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false)
        {
            response.sendRedirect("../Login"); return;
        }
        
        if (request.getParameter("password").length() < 8
            || !request.getParameter("password").equals(request.getParameter("passwordagain")))
        {
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error de validación");
            doGet(request, response);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Pair<String, Integer> hash = Helpers.Login.createNewHash(password);
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String tipo = "aprobador";
        int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
        String sql = "INSERT INTO Usuario (username, password, salt, nombre, direccion, telefono, tipo, idEmpresa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection con = Helpers.DB.newConnection(this))
        {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, hash.getValue0());
                ps.setInt(3, hash.getValue1());
                ps.setString(4, nombre);
                ps.setString(5, direccion);
                ps.setString(6, telefono);
                ps.setString(7, tipo);
                ps.setInt(8, idEmpresa);
                ps.executeUpdate();
            }
            
            request.setAttribute("message", "El usuario " + username + " ha sido registrado exitosamente.");
            doGet(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Aprobador_Alta.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
            doGet(request, response);
        }
    }
}
