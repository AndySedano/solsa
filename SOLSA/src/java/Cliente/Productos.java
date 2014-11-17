/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Helpers.Login;
import java.io.*;
import java.security.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Arturo
 */
public class Productos extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Productos.jsp");
        disp.include(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
