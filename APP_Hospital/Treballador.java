package APP_Hospital;

import java.util.ArrayList;
import java.util.List;

public class Treballador {
    private short id;
    private String nom;
    private String cognoms;
    private Categoria carrec;
    List<Guardies> guardies = new ArrayList<Guardies>();

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
    

    public void reservarGuardia(Guardies guardia) {
        //comprovacions
        guardies.add(guardia);
    }
}
