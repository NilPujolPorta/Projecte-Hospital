package APP_Hospital.model.business.entities;

import java.util.ArrayList;
import java.util.List;

import APP_Hospital.exceptions.AlreadyAdded;
import APP_Hospital.exceptions.CategoryMissmatch;

public class Guardies {
    private int id = -1;
    List<Treballador> trApuntats = new ArrayList<Treballador>();
    //day fromat = YYYYMMDD
    private String dia;
    private short cat;
    private short torn;
    private short zona;
    private short places;
    Treballador treballadorsFinals[] = new Treballador[places];

    public Guardies(int id, List<Treballador> trApuntats, String dia, short cat, short torn, short zona, short places) {//desde bd amb treballadors
        this.id = id;
        this.trApuntats = trApuntats;
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
        this.places = places;
    }

    public Guardies(int id, String dia, short cat, short torn, short zona, short places) {// desde base de dades 
        this.id = id;
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
        this.places = places;
    }

    public Guardies(String dia, short cat, short torn, short zona, short places) {//creacio desde programa
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
        this.places = places;
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

    public short getPlaces(){
        return places;
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }

    public short getCat() {
        return cat;
    }

    public short getTorn() {
        return torn;
    }

    public short getZona() {
        return zona;
    }


    public int getId() {
        return id;
    }
    
    public boolean reservarGuardia(Treballador Treballador) throws AlreadyAdded, CategoryMissmatch{
        if (this.trApuntats.contains(Treballador) ){
            throw new AlreadyAdded();
        } else if (this.getCat() != Treballador.getCat()){
            throw new CategoryMissmatch();
        }
        trApuntats.add(Treballador);
        try{
            Treballador.reservarGuardia(this);
        } catch (AlreadyAdded ex){
            System.out.println("Guardia already added");
            return false;
        }
        
        return true;
    }
    public void triaTreballador(){
        for (Treballador treballador : trApuntats) {
            treballador.calcularPrioritat();

        }
    }

}
