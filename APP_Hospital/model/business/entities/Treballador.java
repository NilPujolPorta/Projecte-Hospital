package APP_Hospital.model.business.entities;

import java.util.ArrayList;
import java.util.List;

import APP_Hospital.exceptions.AlreadyAdded;

public class Treballador {
    private short id;
    private String nom;
    private String cognoms;
    private short idCategoria;
    public int prioritat;
    List<Guardies> guardies = new ArrayList<Guardies>();

   
    public Treballador(short id, String nom, String cognoms, short idCategoria) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.idCategoria = idCategoria;
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
    public short getCat() {
        return idCategoria;
    }

    public int getPrioritat() {
        return prioritat;
    }

    public void setCat(short carrec) {
        this.idCategoria = carrec;
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
