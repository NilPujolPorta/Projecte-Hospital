package APP_Hospital;

import java.util.ArrayList;
import java.util.List;

public class Guardies {
    private int id;
    List<Treballador> TrApuntats = new ArrayList<Treballador>();

    public int getId() {
        return id;
    }
    
    public void reservarGuardia(Treballador Treballador){
        TrApuntats.add(Treballador);
        Treballador.reservarGuardia(this);
    }

}
