package APP_Hospital;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dia {
    private Date data;
    private List<Torn> guardies = new ArrayList<Torn>();


    public Dia(Date data){//des de 0
        this.data = data;
        this.guardies.add(new Torn(1, "unitat1", this, "mati"));
        this.guardies.add(new Torn(2, "unitat1", this, "tarda"));
    }
    public Dia(){//des de DB

    }
}
