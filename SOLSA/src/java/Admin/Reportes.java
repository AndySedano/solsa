package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Reportes extends HttpServlet
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
        try (Connection con = Helpers.DB.newConnection(this))
        {
            java.sql.Date fechaInicio = null;
            java.sql.Date fechaFin = null;
            String sql;
            
            if (request.getParameter("fechaInicio") != null && request.getParameter("fechaFin") != null)
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                request.setAttribute("fechaInicio", request.getParameter("fechaInicio"));
                request.setAttribute("fechaFin", request.getParameter("fechaFin"));
                request.setAttribute("conFechas", true);
                fechaInicio = new java.sql.Date(format.parse(request.getParameter("fechaInicio")).getTime());
                fechaFin = new java.sql.Date(format.parse(request.getParameter("fechaFin")).getTime());
            }
            
            
            
            if (fechaInicio != null)
            {
                sql = "select count(*) as numEntregados from Pedido where estado = 'entregado' "
                    + "and fechaDeEntrega > ? and fechaDeEntrega < ?;";
            }
            else
            {
                sql = "select count(*) as numEntregados from Pedido where estado = 'entregado';";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                if (fechaInicio != null)
                {
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("numEntregados", rs.getInt("numEntregados"));
            }
            
            
            
            if (fechaInicio != null)
            {
                sql = "select count(*) as numTransito from Pedido where estado = 'transito' "
                    + "and fechaDeEntrega > ? and fechaDeEntrega < ?;";
            }
            else
            {
                sql = "select count(*) as numTransito from Pedido where estado = 'transito';";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                if (fechaInicio != null)
                {
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("numTransito", rs.getInt("numTransito"));
            }
            
            
            
            if (fechaInicio != null)
            {
                sql = "select sum(precioTotal) as total from Pedido_PrecioTotal where estado = 'entregado' "
                    + "and fechaDeEntrega > ? and fechaDeEntrega < ?;";
            }
            else
            {
                sql = "select sum(precioTotal) as total from Pedido_PrecioTotal where estado = 'entregado';";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                if (fechaInicio != null)
                {
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("total", Helpers.Money.toString(rs.getInt("total")));
            }
            
            
            
            if (fechaInicio != null)
            {
                sql = "select Empresa.nombre, Empresa.idEmpresa, count(Entregado.precioTotal) as numPedidos, sum(Entregado.precioTotal) as ingresoTotal "
                    + "from Empresa left join "
                    + "(select * from Pedido_PrecioTotal where estado = 'entregado' and fechaDeEntrega > ? and fechaDeEntrega < ?) as Entregado "
                    + "on Empresa.idEmpresa = Entregado.idEmpresa "
                    + "group by Empresa.idEmpresa;";
            }
            else
            {
                sql = "select Empresa.nombre, Empresa.idEmpresa, count(Entregado.precioTotal) as numPedidos, sum(Entregado.precioTotal) as ingresoTotal "
                    + "from Empresa left join "
                    + "(select * from Pedido_PrecioTotal where estado = 'entregado') as Entregado "
                    + "on Empresa.idEmpresa = Entregado.idEmpresa "
                    + "group by Empresa.idEmpresa;";
            }
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                if (fechaInicio != null)
                {
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                
                while(rs.next())
                {
                    Empresa empresa = new Empresa();
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNombre(rs.getString("nombre"));
                    empresa.setNumPedidos(rs.getInt("numPedidos"));
                    empresa.setIngresoTotal(Helpers.Money.toString(rs.getInt("ingresoTotal")));
                    
                    empresas.add(empresa);
                }
            }
        }
        catch (ParseException | SQLException ex)
        {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("empresas", empresas);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Reportes.jsp");
        disp.include(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        //processRequest(request, response);
    }

}
