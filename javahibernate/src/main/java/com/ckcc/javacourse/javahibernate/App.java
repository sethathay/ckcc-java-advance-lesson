package com.ckcc.javacourse.javahibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class App 
{
    public static void main( String[] args )
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/javah?useSSL=false";
        String user = "root";
        String pwd = "";
        
        try {
        	
        	System.out.println("Connection to database: " + jdbcUrl);
        	Connection myConn = DriverManager.getConnection(jdbcUrl, user, pwd);
        	System.out.println("Connection Successfully !!!!!!!!!!!!!!!!!");
        	
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
    }
}
