package Admin;

import Beans.*;
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
            response.sendRedirect("../Login"); return;
        }
        
        List<Empresa> empresas = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            try (PreparedStatement ps = con.prepareStatement("select idEmpresa, nombre from Empresa"))
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
            
            try (PreparedStatement ps = con.prepareStatement("select idProducto, nombre from Producto"))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    producto.setNombre(rs.getString("nombre"));
                    productos.add(producto);
                }
            }
            request.setAttribute("productos", productos);
            
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Departamento_Alta.jsp");
            disp.include(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false)
        {
            response.sendRedirect("../Login"); return;
        }
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            try (PreparedStatement ps = con.prepareStatement("insert into Departamento (idEmpresa, nombre) values (?, ?);", Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, Integer.parseInt(request.getParameter("idEmpresa")));
                ps.setString(2, request.getParameter("nombre"));
                ps.executeUpdate();
                
                try (ResultSet keys = ps.getGeneratedKeys())
                {
                    keys.next();
                    int idDepartamento = keys.getInt(1);
                    
                    for (String idProducto : request.getParameterValues("idProductos"))
                    {
                        try (PreparedStatement ps2 = con.prepareStatement("insert into ProductoDepartamento (idDepartamento, idProducto) values (?, ?);"))
                        {
                            ps2.setInt(1, idDepartamento);
                            ps2.setInt(2, Integer.parseInt(idProducto));
                            ps2.executeUpdate();
                        }
                    }
                }

                request.setAttribute("message", "Alta de departamento exitosa");
                doGet(request, response);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Departamento_Alta.class.getName()).log(Level.SEVERE, null, ex);

            request.setAttribute("error", "true");
            request.setAttribute("message", "Error al dar de alta");
            doGet(request, response);
        }
    }

}
