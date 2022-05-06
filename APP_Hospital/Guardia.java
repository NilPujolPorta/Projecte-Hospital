package APP_Hospital;

public class Guardia {
    private int id;
    private int IDTPrincipal;
    private Torn ParentTorn;
    
    public Guardia(Torn ParentTorn) {//des de 0
        this.id = 1;
        this.ParentTorn = ParentTorn;
    }
    public Guardia(int id, int IDTPrincipal, Torn ParentTorn) {//desde la BD

    }
    public void setTPrincipal(Treballador Treballador) {
        this.IDTPrincipal = Treballador.getId();
    }
}
