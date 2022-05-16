package APP_Hospital;

import java.util.ArrayList;
import java.util.List;

import APP_Hospital.exceptions.AlreadyAdded;

public class Treballador {
    private short id;
    private String nom;
    private String cognoms;
    private Categoria carrec;
    public int prioritat;
    List<Guardies> guardies = new ArrayList<Guardies>();


    public int getPrioritat() {
        return prioritat;
    }
    
    public Treballador(short id, String nom, String cognoms, Categoria carrec) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.carrec = carrec;
    }

    public short getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }
    public Categoria getCarrec() {
        return carrec;
    }

    public void setCarrec(Categoria carrec) {
        this.carrec = carrec;
    }
    

    public void reservarGuardia(Guardies guardia) throws AlreadyAdded {
        if (guardies.contains(guardia)) {
            throw new AlreadyAdded();
        }
        guardies.add(guardia);
    }

    public int calcularPrioritat(){
        //calcul
        return 1;
    }
}
