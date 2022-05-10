package APP_Hospital;

import java.util.ArrayList;
import java.util.List;

public class Guardies {
    private int id;
    List<Treballador> trApuntats = new ArrayList<Treballador>();
    private Dia dia;
    private Categoria cat;
    private Torn torn;
    private Zona zona;

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


    public int getId() {
        return id;
    }
    
    public void reservarGuardia(Treballador Treballador){
        TrApuntats.add(Treballador);
        Treballador.reservarGuardia(this);
    }

}
