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
                sql = "SELECT Usuario.username AS username, Usuario.nombre AS user, Empresa.nombre AS Empresa, Departamento.nombre AS nombreDepartamento FROM Usuario\n" +
                    "INNER JOIN Departamento ON Usuario.idDepartamento=Departamento.idDepartamento \n" +
                    "INNER JOIN Empresa ON Departamento.idEmpresa=Empresa.idEmpresa \n" +
                    "WHERE username=?;";
            }
            else
            {
                sql = "SELECT Usuario.username AS username, Usuario.nombre AS user, Empresa.nombre AS Empresa, Departamento.nombre AS nombreDepartamento FROM Usuario, Departamento, Empresa;";
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
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getString("telefono"));
                    clientes.add(cliente);
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente_Buscar.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        request.setAttribute("clientes", clientes);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Empresa_Buscar.jsp");
        disp.include(request, response);
    }
}
