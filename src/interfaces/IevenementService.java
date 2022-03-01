/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.util.List;
import model.Evenement;
/**
 *
 * @author hazemchtioui
 */
public interface IevenementService {
    
    public void ajouterEvenement(Evenement e);
    
    public List<Evenement> afficherEvenement() ;
    
    public void supprimerEvenement(int id);
    public void modifierEvenement(int id,Evenement e);
    public List<Evenement> chercherEvenement(int id);
    public List<Evenement> TrierEvenement();
    public int PrixVentes  (int id);

 

    
    
    
    
   
}
