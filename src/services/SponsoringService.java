/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IsponsoringService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Sponsoring;
import model.Evenement;
import util.MaConnexion;/**
 *
 * @author hazemchtioui
 */
public class SponsoringService implements IsponsoringService{
    Connection cnx= MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterSponsoring(Sponsoring s) {
       
         String req=" INSERT INTO `sponsoring`(`type_sponsoring`, `entreprise`,  `montant`, `adresse`, `num_tel`) VALUES ('"+s.getType_sponsoring()+"','"+s.getEntreprise()+"','"+s.getMontant()+"','"+s.getAderesse()+"','"+s.getNum_tel()+"')";
           try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("sponsoring ajouter avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
        
    }

    @Override
    public List<Sponsoring> afficherSponsoring() {
           List<Sponsoring> sponsorings=new ArrayList<>();
      String req="SELECT * FROM Sponsoring";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 sponsorings.add(new Sponsoring(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6)));
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
        
    }
      return sponsorings;
    }

    @Override
    public void supprimerSponsoring(int id_sponsoring) {
        String req="DELETE FROM `sponsoring` WHERE id_sponsoring="+id_sponsoring ;
           try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("sponsoring supprimer avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
        
    }

    @Override
    public void modifierSponsoring(int id_sponsoring,Sponsoring s) {
        String req="UPDATE `sponsoring` SET `type_sponsoring`='"+s.getType_sponsoring()+"',`entreprise`='"+s.getEntreprise()+"',`montant`='"+s.getMontant()+"',`adresse`='"+s.getAderesse()+"',`num_tel`='"+s.getNum_tel()+"'  WHERE `id_sponsoring`="+id_sponsoring;
        
        {   
            try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("sponsoring modifier avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
        }
    }

    @Override
    public List<Sponsoring> chercherSponsoring(int id_sponsoring) {
            List<Sponsoring> sponsorings=new ArrayList<>();
      String req="SELECT * FROM SPONSORING where id_sponsoring="+id_sponsoring+"";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 sponsorings.add(new Sponsoring(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6)));
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
    }
      return sponsorings;
        
    }

    @Override
    public List<Sponsoring> TrierSponsoring() {
                  List<Sponsoring> sponsorings=new ArrayList<>();
      String req="SELECT * FROM SPONSORING ORDER BY type_sponsoring";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 sponsorings.add(new Sponsoring(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6)));
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
return sponsorings;

    }

    @Override
    public int calculerMontant(int id_sponsoring,int id) {
        int new_montant=0;
           
       
        String req="SELECT * FROM SPONSORING where id_sponsoring="+id_sponsoring+"";
        String req1="SELECT * FROM EVENEMENT where id="+id+"";
      try {
             //exec
             Statement st=cnx.createStatement();
                          Statement st1=cnx.createStatement();

             ResultSet rs= st.executeQuery(req);
             ResultSet re= st1.executeQuery(req1);
            
                     
             
             while(rs.next() && re.next())
          
             {
                 
                 

                 int montant=rs.getInt("montant");   
                 int nbr_participant=re.getInt("nbr_participant");
               
                 if(nbr_participant>200 ){
                     new_montant=montant+1000;
                     System.out.print("le nouvelle montant est: "+new_montant );
                     
                 }else{
                     System.out.print("aucune plus montant sponsoring");
                     
                 }
                 
             
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
    }
      return new_montant;
    }
}
      
      
    
    

