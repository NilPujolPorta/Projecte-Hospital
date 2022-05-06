package APP_Hospital;

import java.util.ArrayList;
import java.util.List;

public class Torn {
    private int ID;
    private String unitat;
    private Dia dia;
    private String tipus;
    private List<Guardia> guardies = new ArrayList<Guardia>();

    public Torn(int ID, String unitat, Dia dia, String tipus) {//aquest es cuan crees un de nou
        this.ID = ID;
        this.unitat = unitat;
        this.dia = dia;
        this.tipus = tipus;
        if (unitat == "unitat1"){
            this.guardies.add(new Guardia(this));
        }//etc

        //guardar a la BD

    }
    public Torn(int ID, String planta, Dia dia, String tipus, List<Guardia> guardies) {//aquest es cuan l'agafes de la BD
        this.ID = ID;
        this.unitat = planta;
        this.dia = dia;
        this.tipus = tipus;
        this.guardies = guardies;
    }

    public int getID() {
        return ID;
    }
    public String getUnitat() {
        return unitat;
    }
    public Dia getDia() {
        return dia;
    }
    public String getHorari() {
        return tipus;
    }
    public void addGuardia(Guardia guardia) {
        this.guardies.add(guardia);
    }
    public void saveTorn(){
        
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
