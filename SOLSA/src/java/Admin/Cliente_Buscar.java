package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Cliente_Buscar extends HttpServlet
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
        
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            String buscar;
            String sql;
            if ((buscar = request.getParameter("buscar")) != null)
            {
                request.setAttribute("buscar", buscar);
                sql = "SELECT Usuario.username AS username, Usuario.nombre AS nombre, Empresa.nombre AS nombreEmpresa, Departamento.nombre AS nombreDepartamento FROM Empresa \n" +
                        "JOIN Departamento ON Empresa.idEmpresa=Departamento.idEmpresa\n" +
                        "JOIN Usuario ON Departamento.idDepartamento=Usuario.idDepartamento\n" +
                        "WHERE Usuario.username=? AND tipo='cliente';";
            }
            else
            {
                sql = "SELECT Usuario.username AS username, Usuario.nombre AS nombre, Empresa.nombre AS nombreEmpresa, Departamento.nombre AS nombreDepartamento FROM Empresa \n" +
                        "JOIN Departamento ON Empresa.idEmpresa=Departamento.idEmpresa\n" +
                        "JOIN Usuario ON Departamento.idDepartamento=Usuario.idDepartamento\n" +
                        "WHERE tipo='cliente';";
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
                    Cliente cliente = new Cliente();
                    cliente.setUsername(rs.getString("username"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setNombreEmpresa(rs.getString("nombreEmpresa"));
                    cliente.setNombreDepartamento(rs.getString("nombreDepartamento"));
                    clientes.add(cliente);
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente_Buscar.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        request.setAttribute("clientes", clientes);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Cliente_Buscar.jsp");
        disp.include(request, response);
    }
}
