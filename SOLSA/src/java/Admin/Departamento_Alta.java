package Admin;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Departamento_Alta extends HttpServlet 
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
        
        //List<Empresa> empresas = new ArrayList<Empresa>();
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            try (PreparedStatement ps = con.prepareStatement("select idEmpresa, nombre from empresa"))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Departamento_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Departamento_Alta.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {

    }

}
