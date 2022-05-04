package APP_Hospital;

public class Torn {
    private int ID;
    private String planta;
    private Dia dia;
    private String tipus;

    public Torn(int ID, String planta, Dia dia, String tipus) {
        this.ID = ID;
        this.planta = planta;
        this.dia = dia;
        this.tipus = tipus;
    }

    public int getID() {
        return ID;
    }
    public String getPlanta() {
        return planta;
    }
    public Dia getDia() {
        return dia;
    }
    public String getHorari() {
        return tipus;
    }
    

    //END setters i getters
    
    public boolean reservar(Treballador treballador, String posició) {
        //comprova si pot reservar
        return true;
    }
    public boolean cancelar(Treballador treballador){
        //comprova si pot cancelar
        return true;
    }
    
}
