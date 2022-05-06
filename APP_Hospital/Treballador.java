package APP_Hospital;

public class Treballador {
    private short id;
    private String nom;
    private String cognoms;
    private Categoria carrec;
    private int idGuardia;

    public Treballador(short id, String nom, String cognoms, Categoria carrec) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.carrec = carrec;
    }

    public int getIdGuardia() {
        return idGuardia;
    }

    public void setIdGuardia(int idGuardia) {
        this.idGuardia = idGuardia;
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
    
}
