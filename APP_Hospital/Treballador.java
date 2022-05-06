package APP_Hospital;

public class Treballador {
    private short id;
    private String nom;
    private String cognoms;
    private String carrec;
    private short prioritat;

    public Treballador(short id, String nom, String cognoms, String carrec) {
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
    public String getCarrec() {
        return carrec;
    }

    public void setCarrec(String carrec) {
        this.carrec = carrec;
    }

    public short getPrioritat() {
        return prioritat;
    }

    public void setPrioritat(short prioritat) {
        this.prioritat = prioritat;
    }
    
}
