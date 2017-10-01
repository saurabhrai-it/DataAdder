package com.qait.gale;
import java.sql.*;

public class Admin {
   public static Connection connect() throws Exception
   {
     Class.forName("com.mysql.jdbc.Driver");
     return DriverManager.getConnection("jdbc:mysql://localhost:3306/galeocean","root","");
   }
}