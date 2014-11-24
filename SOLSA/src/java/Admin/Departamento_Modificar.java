package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig(fileSizeThreshold = 2* 1024 * 1024,  // 2MB
                 maxFileSize = 10 * 1024 * 1024,      // 10MB
                 maxRequestSize = 50 * 1024 * 1024)   // 50MB
public class Departamento_Modificar extends HttpServlet
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
            int idDepartamento = Integer.parseInt(request.getParameter("id"));
            try (PreparedStatement ps = con.prepareStatement("select * from Departamento where idDepartamento = ?;"))
            {
                ps.setInt(1, idDepartamento);
                ResultSet rs = ps.executeQuery();
                rs.next();
                
                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(idDepartamento);
                departamento.setIdEmpresa(rs.getInt("idEmpresa"));
                departamento.setNombreDepartamento(rs.getString("nombre"));
            
                request.setAttribute("departamento", departamento);
            }
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
            
            String sql = "select Producto.idProducto, Producto.nombre, Seleccionado.idDepartamento "
                       + "from Producto left join "
                       + "(select * from ProductoDepartamento where idDepartamento = ?) as Seleccionado "
                       + "on Producto.idProducto = Seleccionado.idProducto;";
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, idDepartamento);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    producto.setIdDepartamento(rs.getInt("idDepartamento"));
                    producto.setNombre(rs.getString("nombre"));
                    productos.add(producto);
                }
            }
            request.setAttribute("productos", productos);
            
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Departamento_Modificar.jsp");
            disp.include(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Departamento_Modificar.class.getName()).log(Level.SEVERE, null, ex);
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
            try (PreparedStatement ps = con.prepareStatement("update Departamento set nombre = ?, idEmpresa = ? where idDepartamento = ?;"))
            {
                ps.setString(1, request.getParameter("nombre"));
                ps.setInt(2, Integer.parseInt(request.getParameter("idEmpresa")));
                ps.setInt(3, Integer.parseInt(request.getParameter("idDepartamento")));
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement("delete from ProductoDepartamento where idDepartamento = ?;"))
            {
                ps.setInt(1, Integer.parseInt(request.getParameter("idDepartamento")));
                ps.executeUpdate();
            }
            for (String idProducto : request.getParameterValues("idProductos"))
            {
                try (PreparedStatement ps = con.prepareStatement("insert into ProductoDepartamento (idDepartamento, idProducto) values (?, ?);"))
                {
                    ps.setInt(1, Integer.parseInt(request.getParameter("idDepartamento")));
                    ps.setInt(2, Integer.parseInt(idProducto));
                    ps.executeUpdate();
                }
            }
            
            request.setAttribute("message", "Modificación exitosa");
            doGet(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Departamento_Modificar.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error al modificar departamento");
            doGet(request, response);
        }
    }    
}