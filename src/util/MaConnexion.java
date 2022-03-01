/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author hazemchtioui
 */
public class MaConnexion {
       //BD 
    final static String URL ="jdbc:mysql://127.0.0.1:3306/workout";
    final static String USERNAME="root";
    final static String PWD="";
    //att
    private Connection cnx;
    //singleton#1
    static MaConnexion instance= null;
    //constructor
    //singleton#2 :private

    public MaConnexion() {
        try {
            
            cnx=DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("cnx etablie");
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }
    
    //getters setters

    public Connection getCnx() {
        return cnx;
    }
//singleton#3 

    public static MaConnexion getInstance() {
        if(instance==null){
            instance = new MaConnexion();
        }
        return instance;
    }
    /*public connection getConnexion(){
        connection conn;
        try{
            conn = driverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
            return conn;
            
        }catch(Exeption ex){
            System.out.println("error: "+ex.getMessage());
            return null;
        }*/
    }
    
    

