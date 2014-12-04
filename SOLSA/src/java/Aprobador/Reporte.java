package Aprobador;


import Beans.Peticion;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Reporte extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("aprobador") == false)
        {
            response.sendRedirect("../Login"); return;
        }
        
        List<Peticion> peticiones = new ArrayList<>();
        try (Connection con = Helpers.DB.newConnection(this))
        {
            java.sql.Date fechaInicio = null;
            java.sql.Date fechaFin = null;
            String sql;
            String sqlAprobado;
            String sqlRechazado;
            String sqlRealizado;
            
            if (request.getParameter("fechaInicio") != null && request.getParameter("fechaFin") != null)
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                request.setAttribute("fechaInicio", request.getParameter("fechaInicio"));
                request.setAttribute("fechaFin", request.getParameter("fechaFin"));
                fechaInicio = new java.sql.Date(format.parse(request.getParameter("fechaInicio")).getTime());
                fechaFin = new java.sql.Date(format.parse(request.getParameter("fechaFin")).getTime());
            }
                        
            if (fechaInicio != null)
            {
                sql = "select count(*) as numAprobados from Peticion where estado = 'aprobado' "
                    + "and fecha > ? and fecha < ?;";
            }
            else
            {
                sql = "select count(*) as numAprobados from Peticion where estado = 'aprobado';";
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
                request.setAttribute("numAprobados", rs.getInt("numAprobados"));
            }
            
            
            if (fechaInicio != null)
            {
                sql = "select count(*) as numEspera from Peticion where estado = 'espera' "
                    + "and fecha > ? and fecha < ?;";
            }
            else
            {
                sql = "select count(*) as numEspera from Peticion where estado = 'espera';";
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
                request.setAttribute("numEspera", rs.getInt("numEspera"));
            }
            
            if (fechaInicio != null)
            {
                sqlAprobado = "SELECT Empresa.nombre as nombreEmpresa, count(*) as numAprobadas FROM Peticion\n" +
                    "INNER JOIN Carrito ON Peticion.Carrito_idCarrito = Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "INNER JOIN Empresa ON Empresa.idEmpresa = Usuario.idEmpresa\n" +
                    "WHERE Peticion.estado = 'aprobado'\n" +
                    "and fecha > ? and fecha < ? \n" +
                    "GROUP BY Empresa.idEmpresa;";
                sqlRechazado = "SELECT Empresa.nombre as nombreEmpresa, count(*) as numRechazadas FROM Peticion\n" +
                    "INNER JOIN Carrito ON Peticion.Carrito_idCarrito = Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "INNER JOIN Empresa ON Empresa.idEmpresa = Usuario.idEmpresa\n" +
                    "WHERE Peticion.estado = 'rechazado'\n" +
                    "and fecha > ? and fecha < ? \n" +
                    "GROUP BY Empresa.idEmpresa;";
                sqlRealizado = "SELECT Empresa.nombre as nombreEmpresa, count(*) as numRealizadas FROM Peticion\n" +
                    "INNER JOIN Carrito ON Peticion.Carrito_idCarrito = Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "INNER JOIN Empresa ON Empresa.idEmpresa = Usuario.idEmpresa\n" +
                    "WHERE ((Peticion.estado = 'aprobado' OR Peticion.estado = 'rechazado') OR Peticion.estado = 'espera') \n" +
                    "and fecha > ? and fecha < ? \n" +
                    "GROUP BY Empresa.idEmpresa;";
            }
            else
            {
                sqlAprobado = "SELECT Empresa.nombre as nombreEmpresa, count(*) as numAprobadas FROM Peticion\n" +
                    "INNER JOIN Carrito ON Peticion.Carrito_idCarrito = Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "INNER JOIN Empresa ON Empresa.idEmpresa = Usuario.idEmpresa\n" +
                    "WHERE Peticion.estado = 'aprobado'\n" +
                    "GROUP BY Empresa.idEmpresa;";
                sqlRechazado = "SELECT Empresa.nombre as nombreEmpresa, count(*) as numRechazadas FROM Peticion\n" +
                    "INNER JOIN Carrito ON Peticion.Carrito_idCarrito = Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "INNER JOIN Empresa ON Empresa.idEmpresa = Usuario.idEmpresa\n" +
                    "WHERE Peticion.estado = 'rechazado'\n" +
                    "GROUP BY Empresa.idEmpresa;";
                sqlRealizado = "SELECT Empresa.nombre as nombreEmpresa, count(*) as numRealizadas FROM Peticion\n" +
                    "INNER JOIN Carrito ON Peticion.Carrito_idCarrito = Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "INNER JOIN Empresa ON Empresa.idEmpresa = Usuario.idEmpresa\n" +
                    "WHERE ((Peticion.estado = 'aprobado' OR Peticion.estado = 'rechazado') OR Peticion.estado = 'espera') \n" +
                    "GROUP BY Empresa.idEmpresa;";
            }
            try (PreparedStatement ps1 = con.prepareStatement(sqlAprobado)){
                try(PreparedStatement ps2 = con.prepareStatement(sqlRechazado)){
                    try(PreparedStatement ps3 = con.prepareStatement(sqlRealizado)){
                        if (fechaInicio != null)
                        {
                            ps1.setDate(1, fechaInicio);
                            ps1.setDate(2, fechaFin);
                            ps2.setDate(1, fechaInicio);
                            ps2.setDate(2, fechaFin);
                            ps3.setDate(1, fechaInicio);
                            ps3.setDate(2, fechaFin);
                        }
                        ResultSet rs = ps1.executeQuery();

                        while(rs.next())
                        {
                            Peticion peticion = new Peticion();
                            peticion.setNombreEmpresa(rs.getString("nombreEmpresa"));
                            peticion.setNumRealizadas(rs.getInt("numRealizadas"));
                            peticion.setNumAprobadas(rs.getInt("numAprobadas"));
                            peticion.setNumRechazadas(rs.getInt("numRechazadas"));

                            peticiones.add(peticion);
                        }
                    }
                }
            }
            
            
        }catch (ParseException | SQLException ex)
        {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("peticiones", peticiones);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Aprobador/Reporte.jsp");
        disp.include(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        //processRequest(request, response);
    }

}
            
            