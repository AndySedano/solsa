package Aprobador;


import Beans.*;
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
                sql = "select count(*) as numAprobados from Pedido where estado = 'aprobado' "
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
            
            