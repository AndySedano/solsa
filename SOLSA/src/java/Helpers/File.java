package Helpers;

import java.io.*;
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
}
