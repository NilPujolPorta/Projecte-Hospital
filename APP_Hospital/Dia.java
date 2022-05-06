package APP_Hospital;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dia {
    private Date data;
    List<Guardies> guardies = new ArrayList<Guardies>();
    public Dia(Date data){
        this.data = data;
    }
    public Date getData() {
        return data;
    }
}
