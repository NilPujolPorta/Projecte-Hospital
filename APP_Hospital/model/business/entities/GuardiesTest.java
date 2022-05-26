package APP_Hospital.model.business.entities;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import APP_Hospital.exceptions.AlreadyAdded;
import APP_Hospital.exceptions.CategoryMissmatch;

public class GuardiesTest {
    @Test
    public void testReservarGuardia() {
        Guardies guardia = new Guardies("20220202", (short)1, (short)1, (short)1, (short)1);
        Treballador jordi = new Treballador((short)1, "jordi", "padrosa", (short)2);
        Treballador nil = new Treballador((short)1, "nil", "padrosa", (short)2);
        Treballador liu = new Treballador((short)1, "liu", "padrosa", (short)2);

        try {
            guardia.reservarGuardia(jordi);
            guardia.reservarGuardia(nil);
            guardia.reservarGuardia(liu);

        } catch (AlreadyAdded e) {
            // TODO Auto-generated catch block
            System.out.println("AlreadyAdded");
        } catch (CategoryMissmatch e) {
            // TODO Auto-generated catch block
            System.out.println("CategoryMissmatch");
        }
        assertEquals(1, guardia.trApuntats.size());
        assertEquals(3, jordi.guardies.size());
    }

    @Test
    public void testSetDia() {
        //Dia dia = new Dia("20220202");
        //Dia dia2 = new Dia("20220909");
        //Torn torn = new Torn("dia");
        //Zona zona = new Zona("unitat3");
        Guardies guardia = new Guardies("20220202", (short)1, (short)2, (short)4, (short)5);
        Guardies guardia2 = new Guardies("20220202", (short)1, (short)2, (short)4, (short)5);
        guardia.setDia("20220909");
        assertEquals("20220909", guardia.getDia());
    }

    @Test
    public void testSetId() {
        Guardies guardia = new Guardies("20220202", (short)1, (short)2, (short)4, (short)5);
        guardia.setId(1);
        assertEquals(1, guardia.getId());
    }

    @Test
    public void testTriaTreballador() {

    }
}
