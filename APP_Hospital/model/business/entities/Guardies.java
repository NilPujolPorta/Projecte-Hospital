package APP_Hospital;

import java.util.ArrayList;
import java.util.List;

import APP_Hospital.exceptions.AlreadyAdded;
import APP_Hospital.exceptions.CategoryMissmatch;

public class Guardies {
    private int id = -1;
    List<Treballador> trApuntats = new ArrayList<Treballador>();
    private Dia dia;
    private Categoria cat;
    private Torn torn;
    private Zona zona;
    private int places;
    Treballador treballadorsFinals[] = new Treballador[places];

    public Guardies(int id, List<Treballador> trApuntats, Dia dia, Categoria cat, Torn torn, Zona zona, int places) {//desde bd amb treballadors
        this.id = id;
        this.trApuntats = trApuntats;
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
    }

    public Guardies(int id, Dia dia, Categoria cat, Torn torn, Zona zona, int places) {// desde base de dades 
        this.id = id;
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
    }

    public Guardies(Dia dia, Categoria cat, Torn torn, Zona zona, int places) {//creacio desde programa
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

    public int getPlaces(){
        return places;
    }

    public Dia getDia() {
        return dia;
    }
    public void setDia(Dia dia) {
        this.dia = dia;
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
    
    public boolean reservarGuardia(Treballador Treballador) throws AlreadyAdded, CategoryMissmatch{
        if (this.trApuntats.contains(Treballador) ){
            throw new AlreadyAdded();
        } else if (this.getCat() != Treballador.getCarrec()){
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