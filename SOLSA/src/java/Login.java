import java.io.*;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Login.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String base_url  = getServletContext().getInitParameter("url");
        String base_user = getServletContext().getInitParameter("user");
        String base_pass = getServletContext().getInitParameter("pass");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(base_url, base_user, base_pass))
            {
                String tipo = Helpers.Login.verifyLogin(username, password, con);
                String jspUrl = null;
                
                if (tipo == null)
                {
                    jspUrl = "Login";
                }
                else
                {
                    session.setAttribute("username", username);
                    session.setAttribute("tipo", tipo);
                    // enum('admin','aprobador','cliente','superadmin')
                    switch (tipo)
                    {
                        case ("superadmin"):
                        {
                            jspUrl = "SuperAdmin/AddUser";
                            break;
                        }
                        case ("admin"):
                        {
                            jspUrl = "";
                            break;
                        }
                        case ("aprobador"):
                        {
                            jspUrl = "";
                            break;
                        }
                        case ("cliente"):
                        {
                            jspUrl = "Cliente/Productos";
                            break;
                        }
                    }
                }
                response.sendRedirect(jspUrl);
            }
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

//        if (st) {
//
//            request.setAttribute("res", "Bienvenido " + session.getAttribute("nombre"));
//            /*switch (request.getAttribute("departamento").toString()){
//             System.out.println(request.getAttribute("departamento").toString());
//                
//                
//  
//             }*/
//            if (session.getAttribute("departamento").equals("SISTEMASSOLSA")) {
//                RequestDispatcher disp = getServletContext().getRequestDispatcher("/Sistemas/ignorar.html");
//                disp.include(request, response);
//            } else {
//                RequestDispatcher disp = getServletContext().getRequestDispatcher("/gerente/bienvenido.jsp");
//                disp.include(request, response);
//            }
//        } else {
//            request.setAttribute("res", "Usuario o Contrase&ntilde;a Incorrectos");
//            RequestDispatcher disp = request.getRequestDispatcher("/Login.jsp");
//            disp.include(request, response);
//        }
    }
}
