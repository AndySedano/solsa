/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.sql.*;
import java.util.logging.*;
import javax.servlet.http.*;

/**
 *
 * @author Arturo
 */
public class DB
{
    public static Connection newConnection(HttpServlet servlet)
    throws SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection
            (
                servlet.getServletContext().getInitParameter("url"),
                servlet.getServletContext().getInitParameter("user"),
                servlet.getServletContext().getInitParameter("pass")
            );
        }
        catch (ClassNotFoundException ex)
        {
            throw new RuntimeException(ex); // Never happens
        }
    }
}
