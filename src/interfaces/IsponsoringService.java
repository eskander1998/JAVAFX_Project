/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Sponsoring;
import model.Evenement;

/**
 *
 * @author hazemchtioui
 */
public interface IsponsoringService {
    public void ajouterSponsoring(Sponsoring s);
    
    public List<Sponsoring> afficherSponsoring() ;
    
    public void supprimerSponsoring(int id_sponsoring);
    public void modifierSponsoring(int id_sponsoring,Sponsoring s);
    public List<Sponsoring> chercherSponsoring(int id_sponsoring);
    public List<Sponsoring> TrierSponsoring();
    public int calculerMontant  (int id_sponsoring,int id);
    
}
