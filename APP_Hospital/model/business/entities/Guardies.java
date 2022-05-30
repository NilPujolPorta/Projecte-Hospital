package APP_Hospital.model.business.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import APP_Hospital.exceptions.AlreadyAdded;
import APP_Hospital.exceptions.CategoryMissmatch;
import APP_Hospital.model.persistence.dao.impl.JDBCTreballadorDAO;
import APP_Hospital.model.persistence.exceptions.DAOException;

public class Guardies {
    private short id = -1;
    List<Treballador> trApuntats = new ArrayList<Treballador>();
    //day fromat = YYYYMMDD
    private String dia;
    private short cat;
    private short torn;
    private short zona;
    @Override
    public String toString() {
        return "Guardia [cat=" + cat + ", dia=" + dia + ", id=" + id + ", places=" + places + ", torn=" + torn
                + ", zona=" + zona + "]\n";
    }

    private short places;
    Treballador treballadorsFinals[] = new Treballador[places];

    public Guardies(short id, List<Treballador> trApuntats, String dia, short cat, short torn, short zona, short places) {//desde bd amb treballadors
        this.id = id;
        this.trApuntats = trApuntats;
        this.dia = dia;
        this.cat = cat;
        this.torn = torn;
        this.zona = zona;
        this.places = places;
    }

    public Guardies(short id, String dia, short cat, short torn, short zona, short places) {// desde base de dades 
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

    public void setId(short id) {
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


    public short getId() {
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

            try {
                JDBCTreballadorDAO.inscriureGuardia(Treballador, this);
            } catch (DAOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return false;
        }
        try {
            JDBCTreballadorDAO.inscriureGuardia(Treballador, this);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
                return true;
    }

    public void cancelarGuardia(Treballador Treballador) {
        if (this.trApuntats.contains(Treballador) ){
            this.trApuntats.remove(Treballador);
        }
        Treballador.cancelarGuardia(this);
        try {
            JDBCTreballadorDAO.cancelarGuardia(Treballador, this);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void triaTreballador(){
        for (Treballador treballador : trApuntats) {
            treballador.calcularPrioritat();

        }
    }

}
