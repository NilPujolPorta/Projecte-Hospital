package APP_Hospital.model.business.entities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import APP_Hospital.exceptions.AlreadyAdded;

public class TreballadorTest {
    @Test
    public void testCalcularPrioritat() {

    }

    @Test
    public void testReservarGuardia() {
        Guardies guardia = new Guardies("20220202", (short)1, (short)1, (short)1, (short)1);
        Guardies guardia2 = new Guardies("20220202", (short)2, (short)2, (short)2, (short)2);
        Guardies guardia3 = new Guardies("20220202", (short)3, (short)3, (short)3, (short)3);
        Treballador jordi = new Treballador((short)1, "jordi", "padrosa", (short)2);
        
        try {
            jordi.reservarGuardia(guardia);
            //jordi.reservarGuardia(guardia);
            jordi.reservarGuardia(guardia2);
            jordi.reservarGuardia(guardia3);

        } catch (AlreadyAdded e) {
            // TODO Auto-generated catch block
            System.out.println("AlreadyAdded");
        }
        assertEquals(3, jordi.guardies.size());


    }

    @Test
    public void testSetCat() {
        Treballador nashe = new Treballador((short)1, "Peda", "Nashe", (short)2);
        nashe.setCat((short)10);
        assertEquals((short)10, nashe.getCat());
    }
}
