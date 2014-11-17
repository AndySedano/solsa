package Helpers;


import java.security.*;
import java.sql.*;

public class Login
{
    public static String SHA256(String value)
    {
        try
        {
            StringBuilder hexString = new StringBuilder(64);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest((value).getBytes());

            for (int i = 0; i < hash.length; i++)
            {
                if ((0xFF & hash[i]) < 0x10)
                {
                    hexString.append("0").append(Integer.toHexString((0xFF & hash[i])));
                }
                else
                {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            return hexString.toString();
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e); //Never happens
        }
    }
    
    public static String verifyLogin(String username, String password, Connection con)
    throws SQLException
    {
        PreparedStatement stat = con.prepareStatement("select * from Usuario where username=?;");
        stat.setString(1, username);
        ResultSet res = stat.executeQuery();
        if (!res.next())
            return null;
        
        if (res.getString("username").equals(username) == false)
            return null;
        
        int salt = res.getInt("salt");
        
        if (res.getString("password").equals(SHA256(password + salt)) == false)
            return null;
        
        return res.getString("tipo");
    }
}
