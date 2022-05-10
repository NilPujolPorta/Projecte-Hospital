package APP_Hospital;

import java.util.ArrayList;
import java.util.List;

public class Guardies {
    private int id = -1;
    List<Treballador> trApuntats = new ArrayList<Treballador>();
    private Dia dia;
    private Categoria cat;
    private Torn torn;
    private Zona zona;
    private Treballador principal;

    public Guardies(int id, List<Treballador> trApuntats, Dia dia, Categoria cat, Torn torn, Zona zona) {
        this.id = id;
        this.trApuntats = trApuntats;
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
    }

    public Guardies(int id, Dia dia, Categoria cat, Torn torn, Zona zona) {
        this.id = id;
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
    }

    public Guardies(Dia dia, Categoria cat, Torn torn, Zona zona) {
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
    }

    public void setId(int id) {
        if (this.id != -1 || id <= 0) {
            return;
        }
        this.id = id;
    }

    public List<Treballador> getTrApuntats() {
        return trApuntats;
    }

    public Dia getDia() {
        return dia;
    }

    public Categoria getCat() {
        return cat;
    }

    public Torn getTorn() {
        return torn;
    }

    public Zona getZona() {
        return zona;
    }


    public int getId() {
        return id;
    }

    public Treballador getPrincipal() {
        return this.principal;
    }
    
    public boolean reservarGuardia(Treballador Treballador){
        if (this.trApuntats.contains(Treballador) || this.getCat() != Treballador.getCarrec()){
            return false;
        }
        trApuntats.add(Treballador);
        Treballador.reservarGuardia(this);
        return true;
    }
    public void triaTreballador(){
        //comprovacions de prioritat
        //posa el treballador amb mes prioritat a la variable this.principal
    }

}
