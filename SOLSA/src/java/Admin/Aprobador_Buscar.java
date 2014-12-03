package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Aprobador_Buscar extends HttpServlet
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
        
        List<Aprobador> aprobadores = new ArrayList<>();
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            String buscar;
            String sql;
            if ((buscar = request.getParameter("buscar")) != null)
            {
                request.setAttribute("buscar", buscar);
                sql = "SELECT username, nombre, direccion, telefono FROM Usuario WHERE (username LIKE ? OR nombre LIKE ?) AND tipo='aprobador';";
            }
            else
            {
                sql = "SELECT username, nombre, direccion, telefono FROM Usuario WHERE tipo='aprobador';";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                if (buscar != null)
                {
                    ps.setString(1, "%" + buscar + "%");
                    ps.setString(2, "%" + buscar + "%");
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Aprobador aprobador = new Aprobador();
                    aprobador.setUsername(rs.getString("username"));
                    aprobador.setNombre(rs.getString("nombre"));
                    aprobador.setDireccion(rs.getString("direccion"));
                    aprobador.setTelefono(rs.getString("telefono"));
                    aprobadores.add(aprobador);
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Aprobador_Buscar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "true");
            request.setAttribute("message", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
            doGet(request, response);
        }
            
        request.setAttribute("aprobadores", aprobadores);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Aprobador_Buscar.jsp");
        disp.include(request, response);
    }
}
