package APP_Hospital;

public class Guardia {
    private int id;
    private Treballador TPrincipal;
    private Torn ParentTorn;
    
    public Guardia(Torn ParentTorn) {//des de 0
        this.id = 1;
        this.ParentTorn = ParentTorn;
    }
    public Guardia(int id, Treballador TPrincipal, Torn ParentTorn) {//desde la BD

    }
}
