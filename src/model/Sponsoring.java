/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Connection;
import util.MaConnexion;


/**
 *
 * @author hazemchtioui
 */
public class Sponsoring {
    private int id_sponsoring;
    private String type_sponsoring;
    private String entreprise;
    private int montant;
    private String aderesse;
    private String num_tel;
    private int id;

    public Sponsoring(String type_sponsoring, String entreprise, int montant, String aderesse, String num_tel) {
        this.type_sponsoring = type_sponsoring;
        this.entreprise = entreprise;
        this.montant = montant;
        this.aderesse = aderesse;
        this.num_tel = num_tel;
    }

    public Sponsoring() {
    }

    

    public int getId_sponsoring() {
        return id_sponsoring;
    }

    public void setId_sponsoring(int id_sponsoring) {
        this.id_sponsoring = id_sponsoring;
    }

    public String getType_sponsoring() {
        return type_sponsoring;
    }

    public void setType_sponsoring(String type_sponsoring) {
        this.type_sponsoring = type_sponsoring;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getAderesse() {
        return aderesse;
    }

    public void setAderesse(String aderesse) {
        this.aderesse = aderesse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sponsoring{" + "id_sponsoring=" + id_sponsoring + ", type_sponsoring=" + type_sponsoring + ", entreprise=" + entreprise + ", montant=" + montant + ", aderesse=" + aderesse + ", num_tel=" + num_tel + ", id=" + id + '}';
    }
    

    
    
    
    
    
    
    
}
