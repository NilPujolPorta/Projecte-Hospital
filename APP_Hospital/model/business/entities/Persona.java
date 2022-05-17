/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.model.business.entities;

/**
 *
 * @author nilp2
 */
public class Persona extends Entity{
    private String nom;
    private String dni;
    private double edat;

    public Persona(String nom, String dni, double edat) {
        this.nom = nom;
        this.dni = dni;
        this.edat = edat;
    }
    
    @Override
    public String toString() {
        return String.format("DNI: %s\n"
        + "Nom: %s\n"
        + "Edat: %s\n", getDni(), getNom(), getEdat());
    }
    
    
    public String getNom() {
        return nom;
    }
    public double getEdat() {
        return edat;
    }
    public void setEdat(double edat) {
        this.edat = edat;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}

