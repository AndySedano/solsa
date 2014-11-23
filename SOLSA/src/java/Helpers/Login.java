package Helpers;

import java.security.*;
import java.sql.*;
import java.util.*;
import org.javatuples.*;

public class Login
{
    public static Pair<String, Integer> createNewHash(String password)
    {
        Random random = new SecureRandom();
        int salt = random.nextInt();
        String hashed = SHA256(password + salt);
        return Pair.with(hashed, salt);
    }
    
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

    public static String getLogoForClient(Connection con, String username)
    throws SQLException
    {
        String sql = "select Fotografia.idFotografia, Fotografia.nombre\n"
                   + "from Usuario\n"
                   + "join Departamento on Usuario.idDepartamento = Departamento.idDepartamento\n"
                   + "join Empresa on Departamento.idEmpresa = Empresa.idEmpresa\n"
                   + "join Fotografia on Empresa.Fotografia_idFotografia = Fotografia.idFotografia\n"
                   + "where username = ?;";
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery())
            {
                rs.next();
                int idFoto = rs.getInt("idFotografia");
                String nombre = rs.getString("nombre");
                return "/Images/" + idFoto + "/" + nombre;
            }
        }
    }
}
