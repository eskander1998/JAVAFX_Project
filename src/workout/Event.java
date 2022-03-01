/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workout;
import java.util.List;
import model.Evenement;
import services.ServiceEvenement;
import model.Sponsoring;
import services.SponsoringService;
/**
 * @author hazemchtioui
 */
public class Event {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceEvenement es=new ServiceEvenement();
//        Evenement e =new Evenement("MMA","nabeul",80,5,"11/22/2222","22/444/5555","cine.jpg");
//        Evenement e1 =new Evenement("BODY-BUILDING","nabeul",80,0,"11/22/2222","22/444/5555","cine.jpg");
//        
        
        //es.ajouterEvenement(e1);
        //es.ajouterEvenement(e1);
        // System.out.println(es.afficherEvenement()); 
        // es.supprimerEvenement(6);
        //System.out.println(es.chercherEvenement(19)); 
       //System.out.println(es.TrierEvenement()); 
     
       //e.setLieu("radess");
       //es.modifierEvenement(19,e);
       //es.PrixVentes(24) ;
       
       
       SponsoringService ss=new SponsoringService();
        Sponsoring s =new Sponsoring("sportif","peak",8000000,"rue med khames","22 300 200");
            ServiceEvenement cs = new ServiceEvenement();

                List<Evenement> evenements = cs.ListClasse();
                
                for(Evenement eee : evenements)
                {
                                        System.out.println(eee);

                }
                
        //ss.ajouterSponsoring(s);
        
        //System.out.println(ss.afficherSponsoring()); 
        //ss.supprimerSponsoring(1);
       // s.setEntreprise("adidas");
       //ss.modifierSponsoring(2,s);
       //es.PrixVentes(20) ;
       // ss.calculerMontant(2, 19);
        
        //System.out.println(ss.chercherSponsoring(2)); 
        //System.out.println(ss.TrierSponsoring()); 
        
       
    }


    }
    

