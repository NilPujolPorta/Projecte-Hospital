package APP_Hospital.model.business.entities;

import java.util.ArrayList;
import java.util.List;

import APP_Hospital.exceptions.AlreadyAdded;

public class Dia {
    private String data;
    List<Guardies> guardies = new ArrayList<Guardies>();

    public Dia(String data){
        this.data = data;
    }
    
    public String getData() {
        return data;
    }

    public void addGuardies(Guardies guardia) throws AlreadyAdded{
        if (guardies.contains(guardia)) {
            throw new AlreadyAdded();
        }
        guardies.add(guardia);
        guardia.setDia(data);
    }
}
