/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Ludivine
 */
public class Connexion {
     
    private Connection con; 
    
    public Connexion(){
    try {
            //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //step2 create  the connection object  
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.254.3:1521:PFPBS", "GROUPE_29", "GROUPE_29");
            //step3 create the statement object  
            Statement stmt = con.createStatement();
            //step4 execute query  
            ResultSet rs = stmt.executeQuery("select * from PERSONNE");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
//          
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * 
     * @return 
     */
    public Connection getCon(){
        return con;
        
    }
   
}
