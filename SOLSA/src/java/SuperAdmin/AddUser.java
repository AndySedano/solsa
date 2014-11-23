package SuperAdmin;

import java.io.*;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.javatuples.*;

/**
 *
 * @author Arturo
 */
public class AddUser extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("superadmin") == false)
        {
            response.sendRedirect("../Login"); return;
        }
        
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/SuperAdmin/AddUser.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("superadmin") == false)
        {
            response.sendRedirect("../Login"); return;
        }
        
        try (Connection con = Helpers.DB.newConnection(this))
        {
            if (request.getParameter("password").length() < 8
                || !request.getParameter("password").equals(request.getParameter("passwordagain")))
            {
                request.setAttribute("error", "true");
                request.setAttribute("message", "Error de validación");
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/SuperAdmin/AddUser.jsp");
                disp.include(request, response);
                return;
            }
            
            Pair<String, Integer> hash = Helpers.Login.createNewHash(request.getParameter("password"));
            
            PreparedStatement p =
                con.prepareStatement("insert into Usuario (username, password, salt, nombre, direccion, telefono, tipo) values (?, ?, ?, ?, ?, ?, ?)");
            p.setString(1, request.getParameter("username"));
            p.setString(2, hash.getValue0());
            p.setInt   (3, hash.getValue1());
            p.setString(4, request.getParameter("nombre"));
            p.setString(5, request.getParameter("direccion"));
            p.setString(6, request.getParameter("telefono"));
            p.setString(7, "admin");
            p.executeUpdate();
            
            request.setAttribute("message", "Alta de usuario exitosa");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/SuperAdmin/AddUser.jsp");
            disp.include(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
