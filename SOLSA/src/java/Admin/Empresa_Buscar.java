package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Empresa_Buscar extends HttpServlet
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
        
        List<Empresa> empresas = new ArrayList<>();
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            String buscar;
            String sql;
            if ((buscar = request.getParameter("buscar")) != null)
            {
                request.setAttribute("buscar", buscar);
                sql = "select * from Empresa where nombre like ?;";
            }
            else
            {
                sql = "select * from Empresa";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                if (buscar != null)
                {
                    ps.setString(1, "%" + buscar + "%");
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Empresa empresa = new Empresa();
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNombre(rs.getString("nombre"));
                    empresa.setDireccion(rs.getString("direccion"));
                    empresa.setTelefono(rs.getString("telefono"));
                    empresa.setRfc(rs.getString("RFC"));
                    empresas.add(empresa);
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Departamento_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        request.setAttribute("empresas", empresas);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Empresa_Buscar.jsp");
        disp.include(request, response);
    }
}
