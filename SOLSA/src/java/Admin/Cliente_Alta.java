package Admin;

import Beans.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.javatuples.*;

public class Cliente_Alta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login"); return;
        }
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT Departamento.nombre AS nombre, "
                  + "Departamento.idDepartamento AS idDepartamento, "
                  + "Empresa.nombre AS empresa "
                  + "FROM Departamento, Empresa "
                  + "WHERE Departamento.idEmpresa=Empresa.idEmpresa "
                  + "ORDER BY Empresa.nombre, Departamento.nombre;");
            ResultSet rs = ps.executeQuery();
            Map<String, List<Departamento>> empresas = new LinkedHashMap<>();
            String empresaActual = "";
            while (rs.next())
            {
                if (!empresaActual.equals(rs.getString("empresa")))
                    empresaActual = rs.getString("empresa");
                if (!empresas.containsKey(empresaActual))
                    empresas.put(empresaActual, new ArrayList<Departamento>());
                
                Departamento departamento = new Departamento();
                departamento.setNombreDepartamento(rs.getString("nombre"));
                departamento.setIdDepartamento(rs.getInt("idDepartamento"));
                departamento.setNombreEmpresa(rs.getString("empresa"));
                empresas.get(empresaActual).add(departamento);
            }
            
            request.setAttribute("empresas", empresas);
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Cliente_Alta.jsp");
        disp.include(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login"); return;
        }
        
        if (request.getParameter("password").length() < 8
            || !request.getParameter("password").equals(request.getParameter("passwordagain")))
        {
            request.setAttribute("error", "true");
            request.setAttribute("message", "Error de validación");
            doGet(request, response);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Pair<String, Integer> hash = Helpers.Login.createNewHash(password);
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String tipo = "cliente";
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        String sql = "INSERT INTO Usuario (username, password, salt, nombre, direccion, telefono, tipo, idDepartamento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection con = Helpers.DB.newConnection(this))
        {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, hash.getValue0());
                ps.setInt(3, hash.getValue1());
                ps.setString(4, nombre);
                ps.setString(5, direccion);
                ps.setString(6, telefono);
                ps.setString(7, tipo);
                ps.setInt(8, idDepartamento);
                ps.executeUpdate();
            }
            
            request.setAttribute("message", "El usuario " + username + " ha sido registrado exitosamente.");
            doGet(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente_Alta.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
            doGet(request, response);
        }
    }
}
