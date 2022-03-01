/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import model.Evenement;
import model.ReservationEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.MaConnexion;
import util.sqlexcept;

/**
 *
 * @author user
 */
public class ServiceReservationEvent {
    
    private Connection con;
    private Statement ste;

    public ServiceReservationEvent() {
        MaConnexion db=new MaConnexion();
        con = db.getCnx();

    }


    public void ajouter1(ReservationEvent e) throws IOException,sqlexcept
    {
    PreparedStatement pre;
        try {
           
            pre = con.prepareStatement("INSERT INTO `reservation_event` ( `id_organisateur`, `id_client`,`id_event`,`nb_place`,`total`,`mode_paiement`,`etat`) VALUES (?, ?, ?, ?, ?, ?, ?);");
            pre.setInt(1, e.getId_organisateur());
            pre.setInt(2, e.getId_client());
            pre.setInt(3, e.getId_event());
            pre.setInt(4, e.getNb_place());
            pre.setFloat(5, e.getTotal());
            pre.setString(6, e.getMode_paiement());
            pre.setString(7, e.getEtat());
            

    pre.executeUpdate();
        System.out.println("Réservation à l'événement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
 
    }
public void ModifierEvenement (ReservationEvent e) {
    
        try {
            String requete = "update `reservation_event`  set id_organisateur=?,id_client=?,id_event=?,nb_place=?,total=?,mode_paiement=? where ? = id";
            PreparedStatement pre = con.prepareStatement(requete);
            pre.setInt(1, e.getId_organisateur());
            pre.setInt(2, e.getId_client());
            pre.setInt(3, e.getId_event());
            pre.setInt(4, e.getNb_place());
            pre.setFloat(5, e.getTotal());
            pre.setString(6, e.getMode_paiement());
            pre.setInt(7,e.getId());
            

            pre.executeUpdate();
            System.out.println("Réservation à l'événement Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SupprimerEvenement(ReservationEvent cl) {
        try {
            String requete = "delete from `reservation_event`  where ? = id";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("Réservation à l'événement supprimé !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifier_etat(int id,Date date) 
    {

            try {
         String requete = "update `reservation_event`  set etat='effectue' where (? = id and  (DATEDIFF( ?,NOW()) )<=0 )";
            PreparedStatement pre = con.prepareStatement(requete);
                       pre.setInt(1, id);
                       pre.setDate(2, (java.sql.Date) date);

            

            pre.executeUpdate();
            System.out.println("etat l'événement Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
                    
    public List<ReservationEvent> ListReservationEvent() {
        List<ReservationEvent> Mylist = new ArrayList<>();
        try {
            String requete = "select now() ,r.id,r.id_organisateur,r.id_client,r.id_event,r.mode_paiement,r.nb_place,r.total,r.etat, e.id,e.titre,e.date_event"
                    + " from reservation_event r , evenement e  where e.id = r.id_event order by r.etat desc";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            
            while (e.next()) {
                ReservationEvent pre = new ReservationEvent();
               
            pre.setId(e.getInt("r.id"));
            pre.setId_organisateur(e.getInt("r.id_organisateur")); 
            pre.setId_client(e.getInt("r.id_client"));
            pre.setId_event(e.getInt("r.id_event"));
            pre.setMode_paiement(e.getString("r.mode_paiement"));
            pre.setNb_place((e.getInt("r.nb_place")));
            pre.setTotal((e.getInt("r.total")));
            pre.setTitre_event(e.getString("e.titre"));
            pre.setDate_event(e.getDate("e.date_event"));
            
            Date date_event= e.getDate("e.date_event");
            Date jour = e.getDate("now()");
       
            
            
            if(jour.before(date_event)){
                System.out.println(
                    "Date aujourd'hui is before date event");
                pre.setEtat("en cours ");
            } 
            else {
                                   modifier_etat(e.getInt("r.id"),date_event);

                  pre.setEtat("effectue");

            }
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
     public List<ReservationEvent> ListReservationEvent1(int cin) {
        List<ReservationEvent> Mylist = new ArrayList<>();
        try {
            String requete = "select now() ,r.id,r.id_organisateur,r.id_client,r.id_event,r.mode_paiement,r.nb_place,r.total,r.etat, e.id,e.titre,e.date_event"
                    + " from reservation_event r , evenement e  where (  r.id_client = ? && e.id = r.id_event )order by r.etat desc";
            PreparedStatement pst = con.prepareStatement(requete);
                             pst.setInt(1, cin);

            ResultSet e = pst.executeQuery();

            while (e.next()) {
                ReservationEvent pre = new ReservationEvent();
               
            pre.setId(e.getInt("r.id"));
            pre.setId_organisateur(e.getInt("r.id_organisateur")); 
            pre.setId_client(e.getInt("r.id_client"));
            pre.setId_event(e.getInt("r.id_event"));
            pre.setMode_paiement(e.getString("r.mode_paiement"));
            pre.setNb_place((e.getInt("r.nb_place")));
            pre.setTotal((e.getInt("r.total")));
            pre.setTitre_event(e.getString("e.titre"));
            pre.setDate_event(e.getDate("e.date_event"));
            
            Date date_event= e.getDate("e.date_event");
            Date jour = e.getDate("now()");
            
            
            
            
            if(jour.before(date_event)){
                System.out.println(
                    "Date aujourd'hui is before date event");
                pre.setEtat("en cours ");
            } 
            else {
                                   modifier_etat(e.getInt("r.id"),date_event);

                  pre.setEtat("effectue");

            }
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    
    
    
    
}
