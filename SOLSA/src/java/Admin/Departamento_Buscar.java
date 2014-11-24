package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Departamento_Buscar extends HttpServlet
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
        
        Map<String, List<Departamento>> empresas = new LinkedHashMap<>();
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            String buscar;
            String sql;
            if ((buscar = request.getParameter("buscar")) != null)
            {
                request.setAttribute("buscar", buscar);
                sql = "select Departamento.idDepartamento, Departamento.nombre as nombreDepartamento, Empresa.nombre as nombreEmpresa, COUNT(*) as numProductos "
                    + "from ProductoDepartamento "
                    + "join Departamento on ProductoDepartamento.idDepartamento = Departamento.idDepartamento "
                    + "join Empresa on Departamento.idEmpresa = Empresa.idEmpresa "
                    + "where Departamento.nombre like ? "
                    + "or Empresa.nombre like ? "
                    + "group by idDepartamento "
                    + "order by nombreEmpresa";
            }
            else
            {
                sql = "select Departamento.idDepartamento, Departamento.nombre as nombreDepartamento, Empresa.nombre as nombreEmpresa, COUNT(*) as numProductos "
                    + "from ProductoDepartamento "
                    + "join Departamento on ProductoDepartamento.idDepartamento = Departamento.idDepartamento "
                    + "join Empresa on Departamento.idEmpresa = Empresa.idEmpresa "
                    + "group by idDepartamento "
                    + "order by nombreEmpresa";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                if (buscar != null)
                {
                    ps.setString(1, "%" + buscar + "%");
                    ps.setString(2, "%" + buscar + "%");
                }
                ResultSet rs = ps.executeQuery();
                String empresaActual = "";
                while (rs.next())
                {
                    if (!empresaActual.equals(rs.getString("nombreEmpresa")))
                        empresaActual = rs.getString("nombreEmpresa");
                    if (!empresas.containsKey(empresaActual))
                        empresas.put(empresaActual, new ArrayList<Departamento>());

                    Departamento departamento = new Departamento();
                    departamento.setIdDepartamento(rs.getInt("idDepartamento"));
                    departamento.setNombreDepartamento(rs.getString("nombreDepartamento"));
                    departamento.setNombreEmpresa(rs.getString("nombreEmpresa"));
                    departamento.setNumProductos(rs.getInt("numProductos"));
                    empresas.get(empresaActual).add(departamento);
                }

                request.setAttribute("empresas", empresas);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Departamento_Buscar.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        request.setAttribute("empresas", empresas);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Departamento_Buscar.jsp");
        disp.include(request, response);
    }
}
