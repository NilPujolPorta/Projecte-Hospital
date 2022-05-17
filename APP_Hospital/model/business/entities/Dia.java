package APP_Hospital;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import APP_Hospital.exceptions.AlreadyAdded;

public class Dia {
    private Date data;
    List<Guardies> guardies = new ArrayList<Guardies>();

    public Dia(Date data){
        this.data = data;
    }
    
    public Date getData() {
        return data;
    }

    public void addGuardies(Guardies guardia) throws AlreadyAdded{
        if (guardies.contains(guardia)) {
            throw new AlreadyAdded();
        }
        guardies.add(guardia);
        guardia.setDia(this);
    }
}
