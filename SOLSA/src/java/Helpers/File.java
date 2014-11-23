package Helpers;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class File
{
    // source: http://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
    public static String extractFileName(Part part)
    {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items)
        {
            if (s.trim().startsWith("filename"))
            {
                return getLast(getLast(s.substring(s.indexOf("=") + 2, s.length()-1).split("\\\\")).split("/"));
            }
        }
        return "";
    }
    
    private static String getLast(String[] strings)
    {
        return strings[strings.length - 1];
    }

    public static Part getParameter(HttpServletRequest request, String name)
    throws IOException, ServletException
    {
        for (Part part : request.getParts())
        {
            if (part.getName().equals(name))
                return part;
        }
        return null;
    }

    public static String createImageFolder(HttpServlet servlet)
    {
        String path = servlet.getServletContext().getRealPath("") + java.io.File.separator + "Images";
        java.io.File fileSaveDir = new java.io.File(path);
        if (!fileSaveDir.exists())
        {
            fileSaveDir.mkdir();
        }
        return path;
    }

    public static int insertarFoto(HttpServlet servlet, HttpServletRequest request, Connection con, String name)
    throws SQLException, IOException, ServletException
    {
        String path = Helpers.File.createImageFolder(servlet);
        int idFoto;
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO Fotografia (nombre, imagen) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS))
        {
            Part file = Helpers.File.getParameter(request, name);
            String fileName = Helpers.File.extractFileName(file);
            ps.setString(1, fileName);
            ps.setBlob(2, (InputStream)null);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys())
            {
                keys.next();
                idFoto = keys.getInt(1);
                file.write(path + java.io.File.separator + idFoto + "-" + fileName);
            }
        }
        return idFoto;
    }
}
